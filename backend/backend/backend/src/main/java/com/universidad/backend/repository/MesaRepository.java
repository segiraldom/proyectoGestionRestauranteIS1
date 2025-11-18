package com.universidad.backend.repository;

import com.universidad.backend.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Integer> {

    List<Mesa> findBySucursalId(Integer sucursalId);

    List<Mesa> findBySucursalIdAndActivoTrue(Integer sucursalId);

}
