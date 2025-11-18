package com.universidad.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reserva_mesa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ReservaMesaId.class)
public class ReservaMesa {
    
    @Id
    @Column(name = "id_reserva")
    private Integer idReserva;

    @Id
    @Column(name = "id_mesa")
    private Integer idMesa;

    // Relación hacia Reserva (no insertable/updatable porque la PK la maneja el idReserva)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", insertable = false, updatable = false)
    private Reserva reserva;

    // Relación hacia Mesa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mesa", insertable = false, updatable = false)
    private Mesa mesa;

}
