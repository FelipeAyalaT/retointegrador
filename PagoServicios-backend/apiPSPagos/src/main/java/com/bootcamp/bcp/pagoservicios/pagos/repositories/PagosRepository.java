package com.bootcamp.bcp.pagoservicios.pagos.repositories;

import com.bootcamp.bcp.pagoservicios.pagos.entities.Pagos;

import reactor.core.publisher.Flux;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepository extends ReactiveCrudRepository<Pagos,String> {
    Flux<Pagos> findByUsername(String username);
}
