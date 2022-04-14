package com.bootcamp.bcp.pagoservicios.servicioux.entities;

import lombok.*;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Pagos {

    private Integer id;
    private String suministro;
    private BigDecimal monto;
    private Integer servicioId;
    private String username;
    private LocalDateTime fecha;  
}
