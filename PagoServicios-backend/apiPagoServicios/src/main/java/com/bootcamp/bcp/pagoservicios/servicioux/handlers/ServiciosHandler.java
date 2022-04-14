package com.bootcamp.bcp.pagoservicios.servicioux.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bootcamp.bcp.pagoservicios.servicioux.core.exceptions.ServiciosBaseException;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Servicios;
import com.bootcamp.bcp.pagoservicios.servicioux.services.ServiciosService;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.concurrent.TimeUnit;

@Component
public class ServiciosHandler {

    @Autowired
    private ServiciosService serviciosService;

    public Mono<ServerResponse> findAll(ServerRequest request){
    	var tokenHeader = request.headers().header("Authorization");
        //log.info("tokenHeader =" + tokenHeader);
    	return this.serviciosService.findAll()
                .switchIfEmpty(Mono.error(new ServiciosBaseException("No se encontró elementos")))
                .collectList()
                .flatMap(list-> ServerResponse.ok().body(Mono.just(list), Servicios.class));
    }
    
    public Mono<ServerResponse> findByCodCanal(ServerRequest request){
    	var tokenHeader = request.headers().header("Authorization");
        //log.info("tokenHeader =" + tokenHeader);
    	String codCanal = request.pathVariable("codCanal");
    	return this.serviciosService.findByCodCanal(codCanal)
                .switchIfEmpty(Mono.error(new ServiciosBaseException("No se encontró elementos")))
                .collectList()
                .flatMap(list-> ServerResponse.ok()
                		//.cacheControl(CacheControl.maxAge(5, TimeUnit.MINUTES))
                		//.eTag(version)
                		.body(Mono.just(list), Servicios.class));
    }
}
