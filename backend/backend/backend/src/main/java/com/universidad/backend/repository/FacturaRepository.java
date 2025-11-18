package com.universidad.backend.repository;

import com.universidad.backend.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    List<Factura> findByPedidoClienteId(Integer idCliente);

    List<Factura> findByPedidoSucursalId(Integer idSucursal);

}
