package com.universidad.backend.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoIngredienteId implements Serializable {
    private Integer idProducto;
    private Integer idIngrediente;
}
