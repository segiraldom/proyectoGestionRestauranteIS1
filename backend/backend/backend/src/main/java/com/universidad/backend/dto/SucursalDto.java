package com.universidad.backend.dto;

import com.universidad.backend.model.Sucursal;
import lombok.Data;

@Data
public class SucursalDto {
    private Integer id;
    private String nombre;
    private String direccion;
    private String telefono;
    private Integer restauranteId;

    public static SucursalDto fromEntity(Sucursal s) {
        SucursalDto dto = new SucursalDto();
        dto.setId(s.getId());
        dto.setNombre(s.getNombre());
        dto.setDireccion(s.getDireccion());
        dto.setTelefono(s.getTelefono());
        dto.setRestauranteId(s.getRestaurante() != null ? s.getRestaurante().getId() : null);
        return dto;
    }
}
