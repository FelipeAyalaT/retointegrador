package com.bootcamp.bcp.pagoservicios.servicioux.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bootcamp.bcp.pagoservicios.servicioux.core.exceptions.ServiciosBaseException;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Pagos;
import com.bootcamp.bcp.pagoservicios.servicioux.services.PagosService;

import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.concurrent.TimeUnit;

@Component
public class PagosHandler {

    @Autowired
    private PagosService pagosService;

    public Mono<ServerResponse> save(ServerRequest request){

        var pagosInput= request.bodyToMono(Pagos.class);

        return pagosInput
                .flatMap(pagos-> this.pagosService.saveWithValidation(pagos))
                .flatMap(a-> ServerResponse
                        .ok()
                        .contentType(APPLICATION_JSON)
                        .body(Mono.just(a), Pagos.class));
//                .switchIfEmpty(Mono.error(new ServiciosBaseException("Pagos exists")));

    }
    
    public Mono<ServerResponse> findByUsername(ServerRequest request){
    	var tokenHeader = request.headers().header("Authorization");
        //log.info("tokenHeader =" + tokenHeader);
    	String username = request.pathVariable("username");
    	return this.pagosService.findByUsername(username)
                .switchIfEmpty(Mono.error(new ServiciosBaseException("No se encontró elementos")))
                .collectList()
                .flatMap(list-> ServerResponse.ok().body(Mono.just(list), Pagos.class));
    }
}
