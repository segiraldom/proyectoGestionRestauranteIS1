package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "factura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal subtotal;

    @Column(name = "impuesto", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal impuesto;

    @Column(name = "descuento", precision = 10, scale = 2)
    private java.math.BigDecimal descuento;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal total;

    @Column(name = "metodo_pago", nullable = false, length = 20)
    private String metodoPago;

    @Column(name = "fecha", nullable = false)
    private java.time.LocalDateTime fecha;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;
    
}
