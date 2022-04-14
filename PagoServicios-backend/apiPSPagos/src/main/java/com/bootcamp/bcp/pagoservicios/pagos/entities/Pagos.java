package com.bootcamp.bcp.pagoservicios.pagos.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table("pagos")
public class Pagos {
    @Id
    @Column("Id")
    private Integer id;
    @Column("Suministro")
    private String suministro;
    @Column("Monto")
    private BigDecimal monto;
    @Column("ServicioId")
    private Integer servicioId;
    @Column("Username")
    private String username;
    @Column("Fecha")
    private LocalDateTime fecha;
}
