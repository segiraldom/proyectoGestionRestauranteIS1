package com.universidad.backend.model;

import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaMesaId implements Serializable{
    private Integer idReserva;
    private Integer idMesa;
}
