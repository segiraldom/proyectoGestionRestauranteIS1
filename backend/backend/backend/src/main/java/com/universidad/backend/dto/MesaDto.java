package com.universidad.backend.dto;

import com.universidad.backend.model.Mesa;
import lombok.Data;

@Data
public class MesaDto {

    private Integer id;
    private Integer capacidad;
    private Integer numero;
    private String estado;
    private Integer sucursalId;

    public static MesaDto fromEntity(Mesa m) {
        MesaDto dto = new MesaDto();
        dto.setId(m.getId());
        dto.setCapacidad(m.getCapacidad());
        dto.setNumero(m.getNumero());
        dto.setEstado(m.getEstado());
        dto.setSucursalId(m.getSucursal().getId());
        return dto;
    }
}
