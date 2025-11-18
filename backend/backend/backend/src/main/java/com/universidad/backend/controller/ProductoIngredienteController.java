package com.universidad.backend.controller;

import com.universidad.backend.dto.AsociarIngredienteRequest;
import com.universidad.backend.service.ProductoIngredienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/producto-ingredientes")
@RequiredArgsConstructor
public class ProductoIngredienteController {

    private final ProductoIngredienteService service;

    @PostMapping
    public ResponseEntity<String> asociar(@RequestBody AsociarIngredienteRequest req) {
        service.asociar(req.getIdProducto(), req.getIdIngrediente());
        return ResponseEntity.ok("Ingrediente asociado correctamente");
    }

    @DeleteMapping
    public ResponseEntity<Void> eliminar(@RequestBody AsociarIngredienteRequest req) {
        service.eliminarAsociacion(req.getIdProducto(), req.getIdIngrediente());
        return ResponseEntity.noContent().build();
    }
}
