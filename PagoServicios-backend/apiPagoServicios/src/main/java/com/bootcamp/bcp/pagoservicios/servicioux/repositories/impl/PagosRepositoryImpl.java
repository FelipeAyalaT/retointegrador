package com.bootcamp.bcp.pagoservicios.servicioux.repositories.impl;

import com.bootcamp.bcp.pagoservicios.servicioux.core.exceptions.ServiciosBaseException;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Favoritos;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Pagos;
import com.bootcamp.bcp.pagoservicios.servicioux.repositories.PagosRepository;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Repository
public class PagosRepositoryImpl implements PagosRepository {

    private final WebClient client;

    public PagosRepositoryImpl(WebClient.Builder builder,
                              @Value( "${application.urlApiPagos:http://localhost/pagos}" ) String urlApiPagos){
        log.info("urlApiPagos = " + urlApiPagos);

        // Configurar Response timeout
        HttpClient client = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5));
        this.client = builder.baseUrl(urlApiPagos)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    } 


	@Override
	public Flux<Pagos> findByUsername(String username) {
		return this.client.get().uri("/{username}", username).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new ServiciosBaseException("Server error")))
                .bodyToFlux(Pagos.class);
	}


	@Override
	public Mono<Pagos> save(Pagos pago) {
		return this.client.post().uri("/").accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(pago), Pagos.class)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new ServiciosBaseException("Server error")))
                .bodyToMono(Pagos.class)
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(2))
                                .doBeforeRetry(x->  log.info("LOG BEFORE RETRY=" + x))
                                .doAfterRetry(x->  log.info("LOG AFTER RETRY=" + x))
                )
                .doOnError(x-> log.info("LOG ERROR"))
                .doOnSuccess(x-> log.info("LOG SUCCESS"));
	} 
}
