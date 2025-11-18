package com.universidad.backend.dto;

import com.universidad.backend.model.Cliente;
import lombok.Data;

@Data
public class ClienteDto {

    private Integer id;
    private String nombre;
    private String telefono;
    private String direccion;

    public static ClienteDto fromEntity(Cliente c) {
        ClienteDto dto = new ClienteDto();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setTelefono(c.getTelefono());
        dto.setDireccion(c.getDireccion());
        return dto;
    }
}
