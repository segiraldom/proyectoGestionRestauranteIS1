package com.universidad.backend.dto;

import com.universidad.backend.model.Factura;
import lombok.Data;

@Data
public class FacturaDto {

    private Integer id;
    private Integer idPedido;
    private Double subtotal;
    private Double impuesto;
    private Double descuento;
    private Double total;
    private String metodoPago;
    private String fecha;

    public static FacturaDto fromEntity(Factura f) {
        FacturaDto dto = new FacturaDto();
        dto.setId(f.getId());
        dto.setIdPedido(f.getPedido().getId());
        dto.setSubtotal(f.getSubtotal().doubleValue());
        dto.setImpuesto(f.getImpuesto().doubleValue());
        dto.setDescuento(f.getDescuento().doubleValue());
        dto.setTotal(f.getTotal().doubleValue());
        dto.setMetodoPago(f.getMetodoPago());
        dto.setFecha(f.getFecha().toString());
        return dto;
    }
}
