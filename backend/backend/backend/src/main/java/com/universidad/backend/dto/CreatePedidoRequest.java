package com.universidad.backend.dto;

import lombok.Data;

@Data
public class CreatePedidoRequest {

    private Integer idCliente;
    private Integer idEmpleado;
    private Integer idMesa;        // puede ser null si es domicilio
    private Integer idSucursal;

    private String tipoPedido;     // "mesa", "domicilio", "para llevar"
    private String direccionEntrega;
    private String observaciones;
}
