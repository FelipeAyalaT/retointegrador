package com.bootcamp.bcp.pagoservicios.favoritos.services;

import com.bootcamp.bcp.pagoservicios.favoritos.entities.Favoritos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavoritosService {
    Mono<Favoritos> saveWithValidation(Favoritos favoritos);
	Flux<Favoritos> findByUsername(String username);
    
}
