package com.universidad.backend.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompraId implements Serializable {
    private Integer idCompra;
    private Integer idIngrediente;
}
