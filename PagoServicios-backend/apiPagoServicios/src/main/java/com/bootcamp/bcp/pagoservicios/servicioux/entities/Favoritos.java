package com.bootcamp.bcp.pagoservicios.servicioux.entities;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Favoritos {
    private String id;
    private String nombre;
    private String tipoFavorito;
    private Integer serviciosId;
    private String username;
}
