package com.universidad.backend.dto;

import lombok.Data;

@Data
public class AddProductoToPedidoRequest {

    private Integer idProducto;
    private Integer cantidad;
}
