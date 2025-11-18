package com.universidad.backend.dto;

import com.universidad.backend.model.Producto;
import lombok.Data;

@Data
public class ProductoDto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private Double precio;
    private Boolean disponible;

    public static ProductoDto fromEntity(Producto p) {
        ProductoDto dto = new ProductoDto();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setCategoria(p.getCategoria());
        dto.setPrecio(p.getPrecio().doubleValue());
        dto.setDisponible(p.getDisponible());
        return dto;
    }
}
