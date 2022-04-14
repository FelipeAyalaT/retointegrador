package com.bootcamp.bcp.pagoservicios.servicioux.repositories.impl;

import com.bootcamp.bcp.pagoservicios.servicioux.core.exceptions.ServiciosBaseException;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Favoritos;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Pagos;
import com.bootcamp.bcp.pagoservicios.servicioux.repositories.FavoritosRepository;

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
public class FavoritosRepositoryImpl implements FavoritosRepository {

    private final WebClient client;

    public FavoritosRepositoryImpl(WebClient.Builder builder,
                              @Value( "${application.urlApiFavoritos:http://localhost/favoritos}" ) String urlApiFavoritos){
        log.info("urlApiFavoritos = " + urlApiFavoritos);
    // Configurar Response timeout
        HttpClient client = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5));
        this.client = builder.baseUrl(urlApiFavoritos)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }

    @Override
    public Flux<Favoritos> findByUsername(String username) {

        return this.client.get().uri("/{username}", username).accept(MediaType.APPLICATION_JSON)
//                .header("Authorization","Bearer {token}")
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new ServiciosBaseException("Server error")))
                .bodyToFlux(Favoritos.class);
    }

	@Override
	public Mono<Favoritos> save(Favoritos favorito) {
		 return this.client.post().uri("/").accept(MediaType.APPLICATION_JSON)
					.body(Mono.just(favorito), Favoritos.class)
	                .retrieve()
	                .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new ServiciosBaseException("Server error")))
	                .bodyToMono(Favoritos.class)
	                .retryWhen(
	                        Retry.fixedDelay(2, Duration.ofSeconds(2))
	                                .doBeforeRetry(x->  log.info("LOG BEFORE RETRY=" + x))
	                                .doAfterRetry(x->  log.info("LOG AFTER RETRY=" + x))
	                )
	                .doOnError(x-> log.info("LOG ERROR"))
	                .doOnSuccess(x-> log.info("LOG SUCCESS"));
	}
}
