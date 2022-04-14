package com.bootcamp.bcp.pagoservicios.favoritos.services.impl;

import com.bootcamp.bcp.pagoservicios.favoritos.entities.Favoritos;
import com.bootcamp.bcp.pagoservicios.favoritos.repositories.FavoritosRepository;
import com.bootcamp.bcp.pagoservicios.favoritos.services.FavoritosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FavoritosServiceImpl implements FavoritosService {

    @Autowired
    private FavoritosRepository favoritosRepository;

    @Override
    public Mono<Favoritos> saveWithValidation(Favoritos favoritos) {
    	 return this.favoritosRepository.save(favoritos);
    }

	@Override
	public Flux<Favoritos> findByUsername(String username) {
		 return this.favoritosRepository.findByUsername(username);
	}
}
