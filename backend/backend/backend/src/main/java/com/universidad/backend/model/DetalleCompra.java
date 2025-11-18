package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_compra")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(DetalleCompraId.class)
public class DetalleCompra {

    @Id
    @Column(name = "id_compra")
    private Integer idCompra;

    @Id
    @Column(name = "id_ingrediente")
    private Integer idIngrediente;

    @Column(name = "cantidad", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal precioUnitario;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal subtotal;
}
