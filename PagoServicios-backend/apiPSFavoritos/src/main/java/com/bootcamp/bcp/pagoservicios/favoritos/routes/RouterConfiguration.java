package com.bootcamp.bcp.pagoservicios.favoritos.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bootcamp.bcp.pagoservicios.favoritos.entities.Favoritos;
import com.bootcamp.bcp.pagoservicios.favoritos.handlers.FavoritosHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
public class RouterConfiguration {
    @Bean
    @RouterOperations(
        {
            @RouterOperation(path = "/favoritos",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    beanClass = FavoritosHandler.class,
                    beanMethod = "save",
                    operation = @Operation(
                            operationId = "save",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "successful operation",
                                            content = @Content(schema = @Schema(implementation = Favoritos.class)))},
                            parameters = {},
                            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Favoritos.class))))
            ),
            @RouterOperation(path = "/favoritos/{username}",
		            produces = {MediaType.APPLICATION_JSON_VALUE},
		            method = RequestMethod.GET,
		            beanClass = FavoritosHandler.class,
		            beanMethod = "findByUsername",
		            operation = @Operation(
		                    operationId = "findByUsername",
		                    responses = {
		                            @ApiResponse(responseCode = "200",
		                                    description = "successful operation",
		                                    content = @Content(schema = @Schema(implementation = Favoritos.class))),
		                            @ApiResponse(responseCode = "400", description = "Invalid Usuario ID supplied"),
		                            @ApiResponse(responseCode = "404", description = "Employee not found")},
		                    parameters = {
		                            @Parameter(in = ParameterIn.PATH, name = "username" )})
		    ),
        })
    public RouterFunction<ServerResponse> favoritosRoutes(FavoritosHandler favoritosHandler){
        return RouterFunctions.nest(RequestPredicates.path("/favoritos"),
                RouterFunctions
                .route(POST("").and(accept(APPLICATION_JSON)),favoritosHandler::save)
                .andRoute(GET("/{username}"), favoritosHandler::findByUsername)
            );
    }
}
