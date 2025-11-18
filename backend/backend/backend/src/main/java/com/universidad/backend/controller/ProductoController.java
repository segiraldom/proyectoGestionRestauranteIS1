package com.universidad.backend.controller;

import com.universidad.backend.dto.CreateProductoRequest;
import com.universidad.backend.dto.ProductoDto;
import com.universidad.backend.service.ProductoService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    // Crear producto (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductoDto> crear(@RequestBody CreateProductoRequest req) {
        return ResponseEntity.ok(
                ProductoDto.fromEntity(productoService.crear(req))
        );
    }

    // Listar todos (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProductoDto>> listar() {
        return ResponseEntity.ok(
                productoService.listar().stream()
                        .map(ProductoDto::fromEntity)
                        .toList()
        );
    }

    // Listar disponibles (GERENTE y ADMIN)
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/disponibles")
    public ResponseEntity<List<ProductoDto>> listarDisponibles() {
        return ResponseEntity.ok(
                productoService.listarDisponibles().stream()
                        .map(ProductoDto::fromEntity)
                        .toList()
        );
    }

    // Obtener producto
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                ProductoDto.fromEntity(productoService.obtenerPorId(id))
        );
    }

    // Actualizar producto (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizar(
            @PathVariable Integer id,
            @RequestBody CreateProductoRequest req
    ) {
        return ResponseEntity.ok(
                ProductoDto.fromEntity(productoService.actualizar(id, req))
        );
    }

    // Cambiar disponibilidad (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/disponible/{estado}")
    public ResponseEntity<ProductoDto> cambiarDisponibilidad(
            @PathVariable Integer id,
            @PathVariable Boolean estado
    ) {
        return ResponseEntity.ok(
                ProductoDto.fromEntity(productoService.cambiarDisponibilidad(id, estado))
        );
    }

    // Eliminar (soft delete)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
