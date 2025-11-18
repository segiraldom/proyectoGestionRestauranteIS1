package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sucursal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // FK a restaurante.id -> columna id_restaurante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;

    @Column(name = "activo", insertable = false)
    private Boolean activo;
}
