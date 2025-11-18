package com.universidad.backend.service;

import com.universidad.backend.dto.CreateFacturaRequest;
import com.universidad.backend.model.*;
import com.universidad.backend.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaService {

    private final FacturaRepository facturaRepository;
    private final PedidoRepository pedidoRepository;

    @Transactional
    public Factura generarFactura(CreateFacturaRequest req) {

        Pedido pedido = pedidoRepository.findById(req.getIdPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        // Subtotal = suma de subtotales de pedido_producto
        BigDecimal subtotal = pedido.getPedidoProductos().stream()
                .map(PedidoProducto::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal impuesto = subtotal.multiply(BigDecimal.valueOf(0.19)); // IVA del 19%

        BigDecimal descuento = BigDecimal.valueOf(req.getDescuento());

        BigDecimal total = subtotal.add(impuesto).subtract(descuento);

        Factura factura = new Factura();
        factura.setPedido(pedido);
        factura.setSubtotal(subtotal);
        factura.setImpuesto(impuesto);
        factura.setDescuento(descuento);
        factura.setTotal(total);
        factura.setMetodoPago(req.getMetodoPago());
        factura.setFecha(LocalDateTime.now());

        return facturaRepository.save(factura);
    }

    public List<Factura> listar() {
        return facturaRepository.findAll();
    }

    public Factura obtener(Integer id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    public List<Factura> porCliente(Integer idCliente) {
        return facturaRepository.findByPedidoClienteId(idCliente);
    }

    public List<Factura> porSucursal(Integer idSucursal) {
        return facturaRepository.findByPedidoSucursalId(idSucursal);
    }
}
