package com.bootcamp.bcp.pagoservicios.favoritos.repositories;

import com.bootcamp.bcp.pagoservicios.favoritos.entities.Favoritos;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FavoritosRepository extends ReactiveMongoRepository<Favoritos,String> {
    Flux<Favoritos> findByUsername(String username);
}
