package com.universidad.backend.controller;

import com.universidad.backend.dto.CreateFacturaRequest;
import com.universidad.backend.dto.FacturaDto;
import com.universidad.backend.service.FacturaService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@RequiredArgsConstructor
public class FacturaController {

    private final FacturaService facturaService;

    // Crear factura
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE','EMPLEADO')")
    public ResponseEntity<FacturaDto> generar(@RequestBody CreateFacturaRequest req) {
        return ResponseEntity.ok(
                FacturaDto.fromEntity(facturaService.generarFactura(req))
        );
    }

    // Listar todas
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    public ResponseEntity<List<FacturaDto>> listar() {
        return ResponseEntity.ok(
                facturaService.listar().stream().map(FacturaDto::fromEntity).toList()
        );
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                FacturaDto.fromEntity(facturaService.obtener(id))
        );
    }

    // Facturas por cliente
    @GetMapping("/cliente/{idCliente}")
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    public ResponseEntity<List<FacturaDto>> porCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(
                facturaService.porCliente(idCliente).stream().map(FacturaDto::fromEntity).toList()
        );
    }

    // Facturas por sucursal
    @GetMapping("/sucursal/{idSucursal}")
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    public ResponseEntity<List<FacturaDto>> porSucursal(@PathVariable Integer idSucursal) {
        return ResponseEntity.ok(
                facturaService.porSucursal(idSucursal).stream().map(FacturaDto::fromEntity).toList()
        );
    }
}
