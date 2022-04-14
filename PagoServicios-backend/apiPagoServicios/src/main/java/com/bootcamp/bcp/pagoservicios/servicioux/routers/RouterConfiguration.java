package com.bootcamp.bcp.pagoservicios.servicioux.routers;

import com.bootcamp.bcp.pagoservicios.servicioux.entities.Favoritos;
import com.bootcamp.bcp.pagoservicios.servicioux.handlers.FavoritosHandler;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Pagos;
import com.bootcamp.bcp.pagoservicios.servicioux.entities.Servicios;
import com.bootcamp.bcp.pagoservicios.servicioux.handlers.PagosHandler;
import com.bootcamp.bcp.pagoservicios.servicioux.handlers.ServiciosHandler;
import com.bootcamp.bcp.pagoservicios.servicioux.core.exceptions.GlobalExceptionHandler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfiguration {

	@Bean
    @RouterOperations(
        {
            @RouterOperation(
                path = "/servicios",
                produces = {MediaType.APPLICATION_JSON_VALUE},
                method = RequestMethod.GET,
                beanClass = ServiciosHandler.class,
                beanMethod = "findAll",
                operation = @Operation(
                        summary = "Listar Servicios",
                        description="Listar Servicios en general",
                        operationId = "findAll",
                        responses = {
                                @ApiResponse(responseCode = "200",
                                        description = "successful operation",
                                        content = @Content(array=@ArraySchema(schema = @Schema(implementation = Servicios.class)))),
                                @ApiResponse(
                                        responseCode = "404",
                                        description = "No se encontró elementos",
                                        content = @Content(schema = @Schema(implementation= GlobalExceptionHandler.HttpError.class))
                                )
                        },
                        parameters = {
                                //@Parameter(in = ParameterIn.QUERY, name = "search", required = false )
                                })

            ),
            @RouterOperation(path = "/servicios/{codCanal}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = ServiciosHandler.class,
            beanMethod = "findByCodCanal",
            operation = @Operation(
                    operationId = "findByCodCanal",
                    responses = {
                            @ApiResponse(responseCode = "200",
                                    description = "successful operation",
                                    content = @Content(schema = @Schema(implementation = Servicios.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Canal ID supplied"),
                            @ApiResponse(responseCode = "404", description = "Servicio not found")},
                    parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "codCanal" )})
    ),
        })
     
    public RouterFunction<ServerResponse> serviciosRoutes(ServiciosHandler serviciosHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/servicios"),
                RouterFunctions
                        .route(GET(""), serviciosHandler::findAll)
                        .andRoute(GET("/{codCanal}"), serviciosHandler::findByCodCanal)
            );
    }

    
    @Bean
    @RouterOperations(
        {
            @RouterOperation(path = "/pagos",
                    produces = {MediaType.APPLICATION_JSON_VALUE},
                    method = RequestMethod.POST,
                    beanClass = PagosHandler.class,
                    beanMethod = "save",
                    operation = @Operation(
                            operationId = "save",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "successful operation",
                                            content = @Content(schema = @Schema(implementation = Pagos.class)))},
                            parameters = {},
                            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Pagos.class))))
            ),
            @RouterOperation(path = "/pagos/{username}",
		            produces = {MediaType.APPLICATION_JSON_VALUE},
		            method = RequestMethod.GET,
		            beanClass = PagosHandler.class,
		            beanMethod = "findByUsername",
		            operation = @Operation(
		                    operationId = "findByUsername",
		                    responses = {
		                            @ApiResponse(responseCode = "200",
		                                    description = "successful operation",
		                                    content = @Content(schema = @Schema(implementation = Pagos.class))),
		                            @ApiResponse(responseCode = "400", description = "Invalid Usuario ID supplied"),
		                            @ApiResponse(responseCode = "404", description = "Pago not found")},
		                    parameters = {
		                            @Parameter(in = ParameterIn.PATH, name = "username" )})
		    ),
        })
    public RouterFunction<ServerResponse> pagosRoutes(PagosHandler pagosHandler){
        return RouterFunctions.nest(RequestPredicates.path("/pagos"),
                RouterFunctions
                .route(GET("/{username}"), pagosHandler::findByUsername)
                .andRoute(POST("").and(accept(APPLICATION_JSON)), pagosHandler::save)
            );
    }
    
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
