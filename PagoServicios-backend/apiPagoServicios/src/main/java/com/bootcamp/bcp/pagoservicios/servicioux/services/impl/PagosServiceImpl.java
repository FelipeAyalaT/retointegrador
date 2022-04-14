package com.bootcamp.bcp.pagoservicios.servicioux.services.impl;

import com.bootcamp.bcp.pagoservicios.servicioux.core.exceptions.ServiciosBaseException;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Favoritos;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Pagos;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Servicios;
import com.bootcamp.bcp.pagoservicios.servicioux.repositories.FavoritosRepository;
import com.bootcamp.bcp.pagoservicios.servicioux.repositories.PagosRepository;
import com.bootcamp.bcp.pagoservicios.servicioux.repositories.ServiciosRepository;
import com.bootcamp.bcp.pagoservicios.servicioux.services.FavoritosService;
import com.bootcamp.bcp.pagoservicios.servicioux.services.PagosService;
import com.bootcamp.bcp.pagoservicios.servicioux.services.ServiciosService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

@Service
public class PagosServiceImpl implements PagosService {

	@Autowired
    private FavoritosService favoritosService;
	
    @Autowired
    private PagosRepository pagosRepository;
    
    @Autowired
    private ServiciosService serviciosService;
    
    @Override
    public Mono<Pagos> saveWithValidation(Pagos pagos) { 
    	return serviciosService
                .findAll()
                .filter(p->p.getId().equals(pagos.getServicioId()))
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.error(new ServiciosBaseException("Servicio no encontrado")))
                .flatMap(dic -> Mono.just(dic).zipWhen(z->this.pagosRepository.save(pagos)))
                .flatMap(tupla2 -> Mono.just(tupla2).zipWhen(z->
                {
                    pagos.setId(tupla2.getT2().getId());
                	pagos.setFecha(tupla2.getT2().getFecha());
                	
                    Favoritos favorito = new Favoritos ();
                    favorito.setNombre("Pago servicio ".concat(pagos.getSuministro()));
                    favorito.setTipoFavorito(tupla2.getT1().getName());
                    favorito.setServiciosId(pagos.getServicioId());
                    favorito.setUsername(pagos.getUsername());
                    return favoritosService.saveWithValidation(favorito);
                    
                	})).then(Mono.just(pagos));
    }

	@Override
	public Flux<Pagos> findByUsername(String username) {
		return this.pagosRepository.findByUsername(username);
	}}
