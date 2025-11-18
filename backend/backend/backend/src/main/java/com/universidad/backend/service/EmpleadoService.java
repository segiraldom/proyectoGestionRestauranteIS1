package com.universidad.backend.service;

import com.universidad.backend.dto.CreateEmpleadoRequest;
import com.universidad.backend.model.Empleado;
import com.universidad.backend.model.Sucursal;
import com.universidad.backend.repository.EmpleadoRepository;
import com.universidad.backend.repository.SucursalRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final SucursalRepository sucursalRepository;

    // Crear empleado (ADMIN)
    public Empleado crear(CreateEmpleadoRequest req) {

        if (empleadoRepository.existsByCedula(req.getCedula())) {
            throw new RuntimeException("Ya existe un empleado con esta cédula");
        }

        Sucursal sucursal = sucursalRepository.findById(req.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Empleado empleado = new Empleado();
        empleado.setCedula(req.getCedula());
        empleado.setNombre(req.getNombre());
        empleado.setCargo(req.getCargo());
        empleado.setTelefono(req.getTelefono());
        empleado.setSucursal(sucursal);
        empleado.setSueldo(req.getSueldo() != null ? new BigDecimal(req.getSueldo()) : null);
        empleado.setActivo(true);

        return empleadoRepository.save(empleado);
    }

    // Listar todos (ADMIN)
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    // Listar por sucursal (ADMIN y GERENTE)
    public List<Empleado> listarPorSucursal(Integer sucursalId) {
        return empleadoRepository.findBySucursalId(sucursalId);
    }

    // Buscar uno
    public Empleado getById(Integer id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no existe"));
    }

    // Actualizar empleado (ADMIN)
    public Empleado actualizar(Integer id, CreateEmpleadoRequest req) {
        Empleado e = getById(id);

        e.setNombre(req.getNombre());
        e.setCargo(req.getCargo());
        e.setTelefono(req.getTelefono());
        e.setSueldo(new BigDecimal(req.getSueldo()));

        // cambiar sucursal opcionalmente
        if (req.getIdSucursal() != null) {
            Sucursal s = sucursalRepository.findById(req.getIdSucursal())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            e.setSucursal(s);
        }

        return empleadoRepository.save(e);
    }

    // Eliminar (desactivar)
    public void eliminar(Integer id) {
        Empleado e = getById(id);
        e.setActivo(false);
        empleadoRepository.save(e);
    }
}
