package com.bootcamp.bcp.pagoservicios.pagos.repositories;

import com.bootcamp.bcp.pagoservicios.pagos.entities.Servicios;

import reactor.core.publisher.Flux;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiciosRepository extends ReactiveCrudRepository<Servicios,String> {
    Flux<Servicios> findByCodCanal(String codCanal);
}
