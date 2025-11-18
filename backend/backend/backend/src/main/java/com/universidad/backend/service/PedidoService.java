package com.universidad.backend.service;

import com.universidad.backend.dto.AddProductoToPedidoRequest;
import com.universidad.backend.dto.CreatePedidoRequest;
import com.universidad.backend.model.*;
import com.universidad.backend.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final MesaRepository mesaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;
    private final PedidoProductoRepository pedidoProductoRepository;

    @Transactional
    public Pedido crearPedido(CreatePedidoRequest req) {

        Cliente cliente = clienteRepository.findById(req.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Empleado empleado = empleadoRepository.findById(req.getIdEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        Sucursal sucursal = sucursalRepository.findById(req.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Mesa mesa = null;
        if (req.getIdMesa() != null) {
            mesa = mesaRepository.findById(req.getIdMesa())
                    .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        }

        Pedido p = new Pedido();
        p.setCliente(cliente);
        p.setEmpleado(empleado);
        p.setSucursal(sucursal);
        p.setMesa(mesa);

        p.setTipoPedido(req.getTipoPedido());
        p.setEstado("pendiente");
        p.setDireccionEntrega(req.getDireccionEntrega());
        p.setObservaciones(req.getObservaciones());
        p.setFecha(LocalDateTime.now());
        p.setActivo(true);

        return pedidoRepository.save(p);
    }

    @Transactional
    public Pedido agregarProducto(Integer idPedido, AddProductoToPedidoRequest req) {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        Producto producto = productoRepository.findById(req.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        PedidoProducto pp = new PedidoProducto();
        pp.setIdPedido(pedido.getId());
        pp.setIdProducto(producto.getId());

        pp.setPedido(pedido);
        pp.setProducto(producto);
        pp.setCantidad(req.getCantidad());
        pp.setPrecioUnitario(producto.getPrecio());
        pp.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(req.getCantidad())));

        pedidoProductoRepository.save(pp);

        pedido.getPedidoProductos().add(pp);

        return pedido;
    }

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido obtener(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    public List<Pedido> listarPorCliente(Integer idCliente) {
        return pedidoRepository.findByClienteId(idCliente);
    }

    public List<Pedido> listarPorSucursal(Integer idSucursal) {
        return pedidoRepository.findBySucursalId(idSucursal);
    }

    @Transactional
    public Pedido actualizarEstado(Integer idPedido, String nuevoEstado) {
        Pedido p = obtener(idPedido);
        p.setEstado(nuevoEstado);
        return pedidoRepository.save(p);
    }
}
