package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "propina")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura", nullable = false)
    private Factura factura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal monto;

    @Column(name = "tipo", length = 20)
    private String tipo;

    @Column(name = "fecha", nullable = false)
    private java.time.LocalDateTime fecha;
}
