package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cedula", nullable = false, unique = true, length = 15)
    private String cedula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @Column(name = "sueldo", precision = 10, scale = 2)
    private java.math.BigDecimal sueldo;

    @Column(name = "telefono", length = 15)
    private String telefono;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;

    @Column(name = "activo", insertable = false)
    private Boolean activo;
}
