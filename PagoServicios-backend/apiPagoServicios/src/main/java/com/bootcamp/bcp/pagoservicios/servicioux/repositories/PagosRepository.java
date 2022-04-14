package com.bootcamp.bcp.pagoservicios.servicioux.repositories;

import com.bootcamp.bcp.pagoservicios.servicioux.entities.Pagos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepository{
    Flux<Pagos> findByUsername(String username);
    Mono<Pagos> save(Pagos pago);
}
