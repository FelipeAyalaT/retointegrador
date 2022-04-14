package com.bootcamp.bcp.pagoservicios.servicioux;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Servicios Pagos", version = "1.0", description = "Documentation APIs v1.0"))
public class ServiciosuxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciosuxApplication.class, args);
	}

}