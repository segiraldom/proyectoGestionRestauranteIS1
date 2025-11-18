package com.universidad.backend.controller;

import com.universidad.backend.dto.CreateEmpleadoRequest;
import com.universidad.backend.dto.EmpleadoDto;
import com.universidad.backend.service.EmpleadoService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    // Crear empleado (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmpleadoDto> crear(@RequestBody CreateEmpleadoRequest req) {
        return ResponseEntity.ok(
                EmpleadoDto.fromEntity(empleadoService.crear(req))
        );
    }

    // Listar todos (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> listarTodos() {
        return ResponseEntity.ok(
                empleadoService.listarTodos().stream()
                        .map(EmpleadoDto::fromEntity)
                        .toList()
        );
    }

    // Listar por sucursal (ADMIN y GERENTE)
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<EmpleadoDto>> listarPorSucursal(@PathVariable Integer sucursalId) {
        return ResponseEntity.ok(
                empleadoService.listarPorSucursal(sucursalId)
                        .stream()
                        .map(EmpleadoDto::fromEntity)
                        .toList()
        );
    }

    // Obtener uno
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                EmpleadoDto.fromEntity(empleadoService.getById(id))
        );
    }

    // Actualizar (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDto> actualizar(
            @PathVariable Integer id,
            @RequestBody CreateEmpleadoRequest req
    ) {
        return ResponseEntity.ok(
                EmpleadoDto.fromEntity(empleadoService.actualizar(id, req))
        );
    }

    // Eliminar (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        empleadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
