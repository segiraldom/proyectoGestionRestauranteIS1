package com.universidad.backend.repository;

import com.universidad.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByDisponibleTrue();

}
