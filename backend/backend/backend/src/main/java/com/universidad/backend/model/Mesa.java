package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mesa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mesa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal", nullable = false)
    private Sucursal sucursal;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;

    @Column(name = "activo", insertable = false)
    private Boolean activo;

}
