package com.universidad.backend.dto;

import lombok.Data;

@Data
public class CreateMesaRequest {

    private Integer idSucursal;
    private Integer capacidad;
    private Integer numero;
    private String estado; // opcional, por defecto "disponible"

}
