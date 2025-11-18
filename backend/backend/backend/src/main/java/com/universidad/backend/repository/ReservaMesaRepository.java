package com.universidad.backend.repository;

import com.universidad.backend.model.ReservaMesa;
import com.universidad.backend.model.ReservaMesaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaMesaRepository extends JpaRepository<ReservaMesa, ReservaMesaId> {
    List<ReservaMesa> findByIdReserva(Integer idReserva);
    List<ReservaMesa> findByIdMesa(Integer idMesa);
    void deleteByIdReserva(Integer idReserva);
}
