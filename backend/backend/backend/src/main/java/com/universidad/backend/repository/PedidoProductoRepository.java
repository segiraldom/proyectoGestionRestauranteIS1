package com.universidad.backend.repository;

import com.universidad.backend.model.PedidoProducto;
import com.universidad.backend.model.PedidoProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, PedidoProductoId> {

}
