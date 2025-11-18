package com.universidad.backend.dto;

import com.universidad.backend.model.Reserva;
import com.universidad.backend.model.ReservaMesa;
import lombok.Data;

import java.util.List;

@Data
public class ReservaDto {

    private Integer id;
    private Integer idCliente;
    private String fechaHora;
    private String observaciones;
    private List<Integer> idMesas;

    public static ReservaDto fromEntity(Reserva r) {
        ReservaDto dto = new ReservaDto();
        dto.setId(r.getId());
        dto.setIdCliente(r.getCliente().getId());
        dto.setFechaHora(r.getFechaHora().toString());
        dto.setObservaciones(r.getObservaciones());

        dto.setIdMesas(
            r.getReservaMesas() == null ? List.of() :
            r.getReservaMesas().stream()
                .map(ReservaMesa::getIdMesa)
                .toList()
        );

        return dto;
    }
}
