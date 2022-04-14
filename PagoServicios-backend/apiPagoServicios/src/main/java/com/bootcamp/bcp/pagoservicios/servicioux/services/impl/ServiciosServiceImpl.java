package com.bootcamp.bcp.pagoservicios.servicioux.services.impl;

import com.bootcamp.bcp.pagoservicios.servicioux.entities.Servicios;
import com.bootcamp.bcp.pagoservicios.servicioux.repositories.ServiciosRepository;
import com.bootcamp.bcp.pagoservicios.servicioux.services.ServiciosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ServiciosServiceImpl implements ServiciosService {

    @Autowired
    private ServiciosRepository serviciosRepository;
    
    @Override
    public Flux<Servicios> findAll() {
    	return this.serviciosRepository.findAll();
    }

	@Override
	public Flux<Servicios> findByCodCanal(String codCanal) {
		return this.serviciosRepository.findByCodCanal(codCanal);
	}
}
