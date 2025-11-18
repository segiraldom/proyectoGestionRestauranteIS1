package com.universidad.backend.repository;

import com.universidad.backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByClienteId(Integer idCliente);

    List<Pedido> findBySucursalId(Integer idSucursal);

}
