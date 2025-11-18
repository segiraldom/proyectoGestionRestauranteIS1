package com.universidad.backend.repository;

import com.universidad.backend.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByNit(String nit);
}
