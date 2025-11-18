package com.universidad.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateReservaRequest {

    private Integer idCliente;

    private LocalDateTime fechaHora;

    private String observaciones;

    private List<Integer> idMesas; // lista de mesas a reservar

}
