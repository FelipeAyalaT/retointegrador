package com.bootcamp.bcp.pagoservicios.pagos.entities;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table("canal")
public class Canal {
    @Column("CodCanal")
    private String CodCanal;
    @Column("Name")
    private String name;
    @Column("Status")
    private String status;
}
