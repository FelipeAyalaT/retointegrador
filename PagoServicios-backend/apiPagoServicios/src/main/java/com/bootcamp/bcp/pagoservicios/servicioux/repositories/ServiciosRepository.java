package com.bootcamp.bcp.pagoservicios.servicioux.repositories;

import com.bootcamp.bcp.pagoservicios.servicioux.entities.Servicios;

import reactor.core.publisher.Flux;

import org.springframework.stereotype.Repository;

@Repository
public interface ServiciosRepository {
    Flux<Servicios> findByCodCanal(String codCanal);
    Flux<Servicios> findAll();
}
