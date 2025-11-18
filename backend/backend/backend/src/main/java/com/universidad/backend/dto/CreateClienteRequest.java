package com.universidad.backend.dto;

import lombok.Data;

@Data
public class CreateClienteRequest {

    private String nombre;
    private String telefono;
    private String direccion;

}
