package com.universidad.backend.dto;

import lombok.Data;

@Data
public class CreateEmpleadoRequest {
    
    private String cedula;
    private Integer idSucursal;
    private String nombre;
    private String cargo;
    private String telefono;
    private Double sueldo;

}
