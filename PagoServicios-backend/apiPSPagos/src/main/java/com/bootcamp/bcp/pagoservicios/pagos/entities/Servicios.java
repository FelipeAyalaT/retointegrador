package com.bootcamp.bcp.pagoservicios.pagos.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("servicios")
public class Servicios {
    @Id
    private Integer id;
    @Column("Name")
    private String name;
    @Column("CodCanal")
    private String codCanal;
    @Column("Status")
    private String status;
}
