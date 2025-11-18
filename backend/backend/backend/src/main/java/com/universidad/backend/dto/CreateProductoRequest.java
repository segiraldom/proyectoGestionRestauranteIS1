package com.universidad.backend.dto;

import lombok.Data;

@Data
public class CreateProductoRequest {

    private String nombre;
    private String descripcion;
    private String categoria;
    private Double precio;

}
