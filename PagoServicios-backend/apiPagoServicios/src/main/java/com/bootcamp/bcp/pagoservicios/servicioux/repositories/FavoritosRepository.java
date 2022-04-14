package com.bootcamp.bcp.pagoservicios.servicioux.repositories;

import com.bootcamp.bcp.pagoservicios.servicioux.entities.Favoritos;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FavoritosRepository  {
    Flux<Favoritos> findByUsername(String username);
    Mono<Favoritos> save(Favoritos favorito);
}
