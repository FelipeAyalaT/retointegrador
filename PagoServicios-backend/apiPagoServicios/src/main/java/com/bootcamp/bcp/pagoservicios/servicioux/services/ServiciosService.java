package com.bootcamp.bcp.pagoservicios.servicioux.services;

import com.bootcamp.bcp.pagoservicios.servicioux.entities.Servicios;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiciosService {
	Flux<Servicios> findByCodCanal(String codCanal);
	Flux<Servicios> findAll();
}
