package com.universidad.backend.controller;

import com.universidad.backend.dto.CreateClienteRequest;
import com.universidad.backend.dto.ClienteDto;
import com.universidad.backend.service.ClienteService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    // Crear cliente (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClienteDto> crear(@RequestBody CreateClienteRequest req) {
        return ResponseEntity.ok(
                ClienteDto.fromEntity(clienteService.crear(req))
        );
    }

    // Listar clientes (ADMIN, GERENTE)
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping
    public ResponseEntity<List<ClienteDto>> listar() {
        return ResponseEntity.ok(
                clienteService.listar().stream()
                        .map(ClienteDto::fromEntity)
                        .toList()
        );
    }

    // Obtener por ID
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                ClienteDto.fromEntity(clienteService.obtenerPorId(id))
        );
    }

    // Actualizar cliente
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizar(
            @PathVariable Integer id,
            @RequestBody CreateClienteRequest req
    ) {
        return ResponseEntity.ok(
                ClienteDto.fromEntity(clienteService.actualizar(id, req))
        );
    }

    // Eliminar cliente
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
