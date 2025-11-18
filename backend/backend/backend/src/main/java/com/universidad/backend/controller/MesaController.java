package com.universidad.backend.controller;

import com.universidad.backend.dto.CreateMesaRequest;
import com.universidad.backend.dto.MesaDto;
import com.universidad.backend.service.MesaService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    // Crear mesa (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MesaDto> crear(@RequestBody CreateMesaRequest req) {
        return ResponseEntity.ok(
                MesaDto.fromEntity(mesaService.crear(req))
        );
    }

    // Listar todas (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<MesaDto>> listarTodas() {
        return ResponseEntity.ok(
                mesaService.listarTodas().stream()
                        .map(MesaDto::fromEntity)
                        .toList()
        );
    }

    // Listar por sucursal (ADMIN, GERENTE)
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/sucursal/{idSucursal}")
    public ResponseEntity<List<MesaDto>> listarPorSucursal(@PathVariable Integer idSucursal) {
        return ResponseEntity.ok(
                mesaService.listarPorSucursal(idSucursal).stream()
                        .map(MesaDto::fromEntity)
                        .toList()
        );
    }

    // Obtener por ID
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<MesaDto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                MesaDto.fromEntity(mesaService.obtenerPorId(id))
        );
    }

    // Actualizar mesa (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MesaDto> actualizar(
            @PathVariable Integer id,
            @RequestBody CreateMesaRequest req
    ) {
        return ResponseEntity.ok(
                MesaDto.fromEntity(mesaService.actualizar(id, req))
        );
    }

    // Cambiar estado de mesa (ADMIN/GERENTE)
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<MesaDto> cambiarEstado(
            @PathVariable Integer id,
            @PathVariable String estado
    ) {
        return ResponseEntity.ok(
                MesaDto.fromEntity(mesaService.cambiarEstado(id, estado))
        );
    }

    // Eliminar mesa (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        mesaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
