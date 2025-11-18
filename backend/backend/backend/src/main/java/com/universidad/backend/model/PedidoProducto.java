package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pedido_producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PedidoProductoId.class)
public class PedidoProducto {

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;

    @Id
    @Column(name = "id_pedido")
    private Integer idPedido;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal precioUnitario;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;

}
