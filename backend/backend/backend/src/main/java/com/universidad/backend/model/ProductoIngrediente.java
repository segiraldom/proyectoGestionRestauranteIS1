package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto_ingrediente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductoIngredienteId.class)
public class ProductoIngrediente {

    @Id
    @Column(name = "id_producto")
    private Integer idProducto;

    @Id
    @Column(name = "id_ingrediente")
    private Integer idIngrediente;
}
