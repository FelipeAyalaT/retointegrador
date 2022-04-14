package com.bootcamp.bcp.pagoservicios.favoritos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableWebFlux
@OpenAPIDefinition(info = @Info(title = "Favoritos Servicios", version = "1.0", description = "Documentation APIs v1.0"))
public class FavoritosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavoritosApplication.class, args);
	}

}
