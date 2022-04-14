package com.bootcamp.bcp.pagoservicios.pagos.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bootcamp.bcp.pagoservicios.pagos.core.exception.PagosBaseException;
import com.bootcamp.bcp.pagoservicios.pagos.entities.Servicios;
import com.bootcamp.bcp.pagoservicios.pagos.services.ServiciosService;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class ServiciosHandler {

    @Autowired
    private ServiciosService serviciosService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(serviciosService.findAll(), Servicios.class);
    }
    
    public Mono<ServerResponse> findByCodCanal(ServerRequest request){
    	var tokenHeader = request.headers().header("Authorization");
        //log.info("tokenHeader =" + tokenHeader);
    	String codCanal = request.pathVariable("codCanal");
    	return this.serviciosService.findByCodCanal(codCanal)
                .switchIfEmpty(Mono.error(new PagosBaseException("No se encontró elementos")))
                .collectList()
                .flatMap(list-> ServerResponse.ok().body(Mono.just(list), Servicios.class));
    }
}
