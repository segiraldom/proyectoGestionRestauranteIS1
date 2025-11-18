package com.universidad.backend.service;

import com.universidad.backend.dto.CreateProductoRequest;
import com.universidad.backend.model.Producto;
import com.universidad.backend.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    // Crear producto (ADMIN)
    public Producto crear(CreateProductoRequest req) {

        Producto p = new Producto();

        p.setNombre(req.getNombre());
        p.setDescripcion(req.getDescripcion());
        p.setCategoria(req.getCategoria());
        p.setPrecio(BigDecimal.valueOf(req.getPrecio()));
        p.setDisponible(true);

        return productoRepository.save(p);
    }

    // Listar todos los productos (ADMIN)
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // Listar productos disponibles (GERENTE y ADMIN)
    public List<Producto> listarDisponibles() {
        return productoRepository.findByDisponibleTrue();
    }

    // Obtener producto
    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    // Actualizar
    public Producto actualizar(Integer id, CreateProductoRequest req) {

        Producto p = obtenerPorId(id);

        if (req.getNombre() != null) p.setNombre(req.getNombre());
        if (req.getDescripcion() != null) p.setDescripcion(req.getDescripcion());
        if (req.getCategoria() != null) p.setCategoria(req.getCategoria());
        if (req.getPrecio() != null) p.setPrecio(BigDecimal.valueOf(req.getPrecio()));

        return productoRepository.save(p);
    }

    // Cambiar disponibilidad
    public Producto cambiarDisponibilidad(Integer id, Boolean disponible) {
        Producto p = obtenerPorId(id);
        p.setDisponible(disponible);
        return productoRepository.save(p);
    }

    // Soft delete
    public void eliminar(Integer id) {
        Producto p = obtenerPorId(id);
        p.setDisponible(false);
        productoRepository.save(p);
    }
}
