package com.universidad.backend.repository;

import com.universidad.backend.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
