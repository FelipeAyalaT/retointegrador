package com.bootcamp.bcp.pagoservicios.favoritos.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(value="favoritos")
public class Favoritos {
    @Id
    private String id;
    private String nombre;
    private String tipoFavorito;
    private Integer serviciosId;
    private String username;
}
