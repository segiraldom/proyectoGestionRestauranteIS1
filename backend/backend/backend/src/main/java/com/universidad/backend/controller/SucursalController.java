package com.universidad.backend.controller;

import com.universidad.backend.dto.CreateSucursalRequest;
import com.universidad.backend.dto.SucursalDto;
import com.universidad.backend.model.Sucursal;
import com.universidad.backend.service.SucursalService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService sucursalService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SucursalDto> create(@RequestBody CreateSucursalRequest req) {
        Sucursal nueva = sucursalService.crear(req.getRestauranteId(), req.toEntity());
        return ResponseEntity.ok(SucursalDto.fromEntity(nueva));
    }

    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping
    public ResponseEntity<List<SucursalDto>> listAll() {
        return ResponseEntity.ok(
                sucursalService.listAll()
                        .stream()
                        .map(SucursalDto::fromEntity)
                        .toList()
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<SucursalDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(SucursalDto.fromEntity(sucursalService.getById(id)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/por-restaurante/{restauranteId}")
    public ResponseEntity<List<SucursalDto>> listByRestaurante(@PathVariable Integer restauranteId) {
        return ResponseEntity.ok(
                sucursalService.listByRestauranteId(restauranteId)
                        .stream()
                        .map(SucursalDto::fromEntity)
                        .toList()
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> update(@PathVariable Integer id, @RequestBody CreateSucursalRequest req) {
        Sucursal updated = sucursalService.update(id, req.toEntity());
        return ResponseEntity.ok(SucursalDto.fromEntity(updated));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
