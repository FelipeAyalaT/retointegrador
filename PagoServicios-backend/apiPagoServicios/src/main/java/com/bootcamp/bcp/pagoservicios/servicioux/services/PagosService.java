package com.bootcamp.bcp.pagoservicios.servicioux.services;

import com.bootcamp.bcp.pagoservicios.servicioux.entities.Pagos;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PagosService {
	Flux<Pagos> findByUsername(String username);
    Mono<Pagos> saveWithValidation(Pagos author);
}
