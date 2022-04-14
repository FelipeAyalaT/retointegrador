package com.bootcamp.bcp.pagoservicios.servicioux.repositories.impl;

import com.bootcamp.bcp.pagoservicios.servicioux.core.exceptions.ServiciosBaseException;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Servicios;
import com.bootcamp.bcp.pagoservicios.servicioux.repositories.ServiciosRepository;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Signal;
import reactor.core.publisher.SignalType;
import reactor.cache.CacheFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ServiciosRepositoryImpl implements ServiciosRepository {

    private final WebClient client;
    
    private Cache<String, List<Servicios>> caffeineCache;
    
    public ServiciosRepositoryImpl(WebClient.Builder builder,
                              @Value( "${application.urlApiServicios:http://localhost/servicios}" ) String urlApiServicios){
        log.info("urlApiServicios = " + urlApiServicios);

        // Configurar Response timeout
        HttpClient client = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5));
        this.client = builder.baseUrl(urlApiServicios)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
        
        this.caffeineCache = Caffeine.newBuilder().maximumSize(100)
                .expireAfterWrite(Duration.ofSeconds(300)).build();
    } 


	@Override
	public Flux<Servicios> findByCodCanal(String codCanal) {
	                      return  findAll()
	                          .filter(x->x.getCodCanal().equals(codCanal)); 
	}


	@Override
	public Flux<Servicios> findAll() { 
		 final Flux<Servicios> cachedFluxCaffeine = CacheFlux
	                .lookup(
	                    k -> {
	                      final List<Servicios> cached = caffeineCache.getIfPresent(k);

	                      if (cached == null) {
	                        return Mono.empty();
	                      }

	                      return Mono.just(cached)
	                          .flatMapMany(Flux::fromIterable)
	                          .map(Signal::next)
	                          .collectList();
	                    },
	                    "ALL"
	                )
	                .onCacheMissResume(
	                		this.client.get().uri("/").accept(MediaType.APPLICATION_JSON)
	                        .retrieve()
	                        .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new ServiciosBaseException("Server error")))
	                        .bodyToFlux(Servicios.class)
	                		)
	                .andWriteWith((k, sig) -> Mono.fromRunnable(() ->
	                    caffeineCache.put(
	                        k,
	                        sig.stream()
	                            .filter(signal -> signal.getType() == SignalType.ON_NEXT)
	                            .map(Signal::get)
	                            .collect(Collectors.toList())
	                    )
	                ));

	            return cachedFluxCaffeine;
	}
}
