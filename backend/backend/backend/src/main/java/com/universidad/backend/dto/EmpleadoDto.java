package com.universidad.backend.dto;

import com.universidad.backend.model.Empleado;
import lombok.Data;

@Data
public class EmpleadoDto {

    private Integer id;
    private String cedula;
    private String nombre;
    private String cargo;
    private String telefono;
    private Double sueldo;
    private Integer sucursalId;

    public static EmpleadoDto fromEntity(Empleado e) {
        EmpleadoDto dto = new EmpleadoDto();
        dto.setId(e.getId());
        dto.setCedula(e.getCedula());
        dto.setNombre(e.getNombre());
        dto.setCargo(e.getCargo());
        dto.setTelefono(e.getTelefono());
        dto.setSueldo(e.getSueldo() != null ? e.getSueldo().doubleValue() : null);
        dto.setSucursalId(e.getSucursal() != null ? e.getSucursal().getId() : null);

        return dto;
    }
}
