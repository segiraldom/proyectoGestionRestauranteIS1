package com.universidad.backend.repository;

import com.universidad.backend.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByClienteId(Integer idCliente);
    List<Reserva> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
