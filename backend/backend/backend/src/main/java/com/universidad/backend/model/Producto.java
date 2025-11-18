package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal precio;

    @Column(name = "disponible", insertable = false)
    private Boolean disponible;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;
}
