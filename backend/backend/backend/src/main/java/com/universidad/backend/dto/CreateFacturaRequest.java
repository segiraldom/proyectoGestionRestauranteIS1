package com.universidad.backend.dto;

import lombok.Data;

@Data
public class CreateFacturaRequest {

    private Integer idPedido;
    private Double descuento = 0.0;
    private String metodoPago; // "efectivo", "tarjeta", "transferencia"
}
