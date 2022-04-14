package com.bootcamp.bcp.pagoservicios.favoritos.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bootcamp.bcp.pagoservicios.favoritos.core.exception.FavoritosBaseException;
import com.bootcamp.bcp.pagoservicios.favoritos.entities.Favoritos;
import com.bootcamp.bcp.pagoservicios.favoritos.services.FavoritosService;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class FavoritosHandler {

    @Autowired
    private FavoritosService favoritosService;

    public Mono<ServerResponse> save(ServerRequest request){

        var favoritosInput= request.bodyToMono(Favoritos.class);

        return favoritosInput
                .flatMap(favoritos-> this.favoritosService.saveWithValidation(favoritos))
                .flatMap(a-> ServerResponse
                        .ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(a), Favoritos.class));
//                .switchIfEmpty(Mono.error(new favoritosExistsException("favoritos exists")));

    }
    
    public Mono<ServerResponse> findByUsername(ServerRequest request){
    	var tokenHeader = request.headers().header("Authorization");
        //log.info("tokenHeader =" + tokenHeader);
    	String username = request.pathVariable("username");
    	return this.favoritosService.findByUsername(username)
                .switchIfEmpty(Mono.error(new FavoritosBaseException("No se encontró elementos")))
                .collectList()
                .flatMap(list-> ServerResponse.ok().body(Mono.just(list), Favoritos.class));
    }
}
