package com.bootcamp.bcp.pagoservicios.pagos.services.impl;

import com.bootcamp.bcp.pagoservicios.pagos.entities.Pagos;
import com.bootcamp.bcp.pagoservicios.pagos.repositories.PagosRepository;
import com.bootcamp.bcp.pagoservicios.pagos.services.PagosService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PagosServiceImpl implements PagosService {

    @Autowired
    private PagosRepository pagosRepository;

    @Override
    public Mono<Pagos> saveWithValidation(Pagos pagos) {
    	pagos.setFecha(LocalDateTime.now());
    	 return this.pagosRepository.save(pagos);
    }
    
    @Override
	public Flux<Pagos> findByUsername(String username) {
		return this.pagosRepository.findByUsername(username);
	}
}
