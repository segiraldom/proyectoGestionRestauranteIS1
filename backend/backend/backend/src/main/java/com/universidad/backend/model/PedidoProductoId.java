package com.universidad.backend.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoProductoId implements Serializable {
    private Integer idProducto;
    private Integer idPedido;
}
