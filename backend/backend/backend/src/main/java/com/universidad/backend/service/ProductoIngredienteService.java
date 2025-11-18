package com.universidad.backend.service;

import com.universidad.backend.model.ProductoIngrediente;
import com.universidad.backend.repository.ProductoIngredienteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoIngredienteService {

    private final ProductoIngredienteRepository repo;

    public ProductoIngrediente asociar(Integer idProducto, Integer idIngrediente) {
        ProductoIngrediente pi = new ProductoIngrediente(idProducto, idIngrediente);
        return repo.save(pi);
    }

    public List<ProductoIngrediente> ingredientesDeProducto(Integer idProducto) {
        return repo.findByIdProducto(idProducto);
    }

    public void eliminarAsociacion(Integer idProducto, Integer idIngrediente) {
        repo.deleteById(new com.universidad.backend.model.ProductoIngredienteId(idProducto, idIngrediente));
    }
}
