package com.bootcamp.bcp.pagoservicios.pagos.services;

import com.bootcamp.bcp.pagoservicios.pagos.entities.Servicios;

import reactor.core.publisher.Flux;

public interface ServiciosService {
	Flux<Servicios> findByCodCanal(String codCanal);
    Flux<Servicios> findAll();
}
