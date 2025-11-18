package com.universidad.backend.dto;

import lombok.Data;

@Data
public class AsociarIngredienteRequest {
    private Integer idProducto;
    private Integer idIngrediente;
}
