package com.universidad.backend.dto;

import com.universidad.backend.model.Pedido;
import com.universidad.backend.model.PedidoProducto;
import lombok.Data;

import java.util.List;

@Data
public class PedidoDto {

    private Integer id;
    private Integer idCliente;
    private Integer idEmpleado;
    private Integer idMesa;
    private Integer idSucursal;

    private String tipoPedido;
    private String estado;
    private String direccionEntrega;
    private String observaciones;
    private String fecha;

    private List<PedidoProductoItem> productos;

    @Data
    public static class PedidoProductoItem {
        private Integer idProducto;
        private Integer cantidad;
        private Double precioUnitario;
        private Double subtotal;
    }

    public static PedidoDto fromEntity(Pedido p) {

        PedidoDto dto = new PedidoDto();

        dto.setId(p.getId());
        dto.setIdCliente(p.getCliente().getId());
        dto.setIdEmpleado(p.getEmpleado().getId());
        dto.setIdMesa(p.getMesa() != null ? p.getMesa().getId() : null);
        dto.setIdSucursal(p.getSucursal().getId());

        dto.setTipoPedido(p.getTipoPedido());
        dto.setEstado(p.getEstado());
        dto.setDireccionEntrega(p.getDireccionEntrega());
        dto.setObservaciones(p.getObservaciones());
        dto.setFecha(p.getFecha().toString());

        dto.setProductos(
                p.getPedidoProductos()
                        .stream()
                        .map(pp -> {
                            PedidoProductoItem item = new PedidoProductoItem();
                            item.setIdProducto(pp.getProducto().getId());
                            item.setCantidad(pp.getCantidad());
                            item.setPrecioUnitario(pp.getPrecioUnitario().doubleValue());
                            item.setSubtotal(pp.getSubtotal().doubleValue());
                            return item;
                        })
                        .toList()
        );

        return dto;
    }
}
