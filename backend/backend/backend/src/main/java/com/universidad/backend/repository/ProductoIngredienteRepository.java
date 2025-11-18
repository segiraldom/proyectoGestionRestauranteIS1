package com.universidad.backend.repository;

import com.universidad.backend.model.ProductoIngrediente;
import com.universidad.backend.model.ProductoIngredienteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoIngredienteRepository
        extends JpaRepository<ProductoIngrediente, ProductoIngredienteId> {

    List<ProductoIngrediente> findByIdProducto(Integer idProducto);

    List<ProductoIngrediente> findByIdIngrediente(Integer idIngrediente);
}
