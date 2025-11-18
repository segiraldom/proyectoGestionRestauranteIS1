package com.universidad.backend.repository;

import com.universidad.backend.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    List<Empleado> findBySucursalId(Integer idSucursal);

    boolean existsByCedula(String cedula);

}
