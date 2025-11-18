package com.universidad.backend.repository;

import com.universidad.backend.model.Sucursal;
import com.universidad.backend.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    List<Sucursal> findByRestaurante(Restaurante restaurante);
}
