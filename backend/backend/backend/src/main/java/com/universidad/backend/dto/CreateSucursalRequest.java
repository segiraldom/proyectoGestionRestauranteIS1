package com.universidad.backend.dto;

import com.universidad.backend.model.Sucursal;
import lombok.Data;

@Data
public class CreateSucursalRequest {
    private Integer restauranteId;
    private String nombre;
    private String direccion;
    private String telefono;

    public Sucursal toEntity() {
        Sucursal s = new Sucursal();
        s.setNombre(this.nombre);
        s.setDireccion(this.direccion);
        s.setTelefono(this.telefono);
        return s;
    }
}
