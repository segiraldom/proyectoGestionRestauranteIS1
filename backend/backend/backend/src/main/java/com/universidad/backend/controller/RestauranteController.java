package com.universidad.backend.controller;

import com.universidad.backend.dto.CreateRestauranteRequest;
import com.universidad.backend.dto.RestauranteDto;
import com.universidad.backend.model.Restaurante;
import com.universidad.backend.service.RestauranteService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteService restauranteService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<RestauranteDto> create(@RequestBody CreateRestauranteRequest req) {
        Restaurante r = new Restaurante();
        r.setNombre(req.getNombre());
        r.setNit(req.getNit());
        Restaurante saved = restauranteService.crear(r);
        return ResponseEntity.ok(RestauranteDto.fromEntity(saved));
    }

    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping
    public ResponseEntity<List<RestauranteDto>> listAll() {
        return ResponseEntity.ok(
                restauranteService.listar()
                        .stream()
                        .map(RestauranteDto::fromEntity)
                        .toList()
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(RestauranteDto.fromEntity(restauranteService.obtenerPorId(id)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDto> update(@PathVariable Integer id, @RequestBody CreateRestauranteRequest req) {
        Restaurante datos = new Restaurante();
        datos.setNombre(req.getNombre());
        datos.setNit(req.getNit());
        Restaurante updated = restauranteService.actualizar(id, datos);
        return ResponseEntity.ok(RestauranteDto.fromEntity(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        restauranteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
