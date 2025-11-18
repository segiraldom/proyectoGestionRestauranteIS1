package com.universidad.backend.controller;

import com.universidad.backend.dto.*;
import com.universidad.backend.model.Pedido;
import com.universidad.backend.service.PedidoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    // Crear pedido
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLEADO','CLIENTE')")
    public ResponseEntity<PedidoDto> crear(@RequestBody CreatePedidoRequest req) {
        return ResponseEntity.ok(
                PedidoDto.fromEntity(pedidoService.crearPedido(req))
        );
    }

    // Agregar productos al pedido
    @PostMapping("/{idPedido}/productos")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLEADO')")
    public ResponseEntity<PedidoDto> agregarProducto(
            @PathVariable Integer idPedido,
            @RequestBody AddProductoToPedidoRequest req
    ) {
        Pedido p = pedidoService.agregarProducto(idPedido, req);
        return ResponseEntity.ok(PedidoDto.fromEntity(p));
    }

    // Listar pedidos
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    public ResponseEntity<List<PedidoDto>> listar() {
        return ResponseEntity.ok(
                pedidoService.listar().stream().map(PedidoDto::fromEntity).toList()
        );
    }

    // Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(
                PedidoDto.fromEntity(pedidoService.obtener(id))
        );
    }

    // Listar por cliente
    @GetMapping("/cliente/{idCliente}")
    @PreAuthorize("hasAnyRole('ADMIN','CLIENTE')")
    public ResponseEntity<List<PedidoDto>> porCliente(@PathVariable Integer idCliente) {
        return ResponseEntity.ok(
                pedidoService.listarPorCliente(idCliente).stream().map(PedidoDto::fromEntity).toList()
        );
    }

    // Listar por sucursal
    @GetMapping("/sucursal/{idSucursal}")
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE')")
    public ResponseEntity<List<PedidoDto>> porSucursal(@PathVariable Integer idSucursal) {
        return ResponseEntity.ok(
                pedidoService.listarPorSucursal(idSucursal).stream().map(PedidoDto::fromEntity).toList()
        );
    }

    // Actualizar estado
    @PutMapping("/{id}/estado")
    @PreAuthorize("hasAnyRole('ADMIN','GERENTE','EMPLEADO')")
    public ResponseEntity<PedidoDto> actualizarEstado(
            @PathVariable Integer id,
            @RequestParam String estado
    ) {
        return ResponseEntity.ok(
                PedidoDto.fromEntity(pedidoService.actualizarEstado(id, estado))
        );
    }
}
