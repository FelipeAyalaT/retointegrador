package com.bootcamp.bcp.pagoservicios.servicioux.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servicios {
    private Integer id;
    private String name;
    private String codCanal;
    private String status;
}
