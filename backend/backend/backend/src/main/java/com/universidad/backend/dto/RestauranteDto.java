package com.universidad.backend.dto;

import com.universidad.backend.model.Restaurante;

import lombok.Data;

@Data
public class RestauranteDto {
    private Integer id;
    private String nombre;
    private String nit;

    public static RestauranteDto fromEntity(Restaurante r) {
        RestauranteDto dto = new RestauranteDto();
        dto.setId(r.getId());
        dto.setNombre(r.getNombre());
        dto.setNit(r.getNit());
        return dto;
    }
}
