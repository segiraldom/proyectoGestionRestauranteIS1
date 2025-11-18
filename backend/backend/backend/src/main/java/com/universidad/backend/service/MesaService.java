package com.universidad.backend.service;

import com.universidad.backend.dto.CreateMesaRequest;
import com.universidad.backend.model.Mesa;
import com.universidad.backend.model.Sucursal;
import com.universidad.backend.repository.MesaRepository;
import com.universidad.backend.repository.SucursalRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final SucursalRepository sucursalRepository;

    // Crear una mesa
    public Mesa crear(CreateMesaRequest req) {

        Sucursal sucursal = sucursalRepository.findById(req.getIdSucursal())
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));

        Mesa m = new Mesa();
        m.setSucursal(sucursal);
        m.setCapacidad(req.getCapacidad());
        m.setNumero(req.getNumero());
        m.setEstado(req.getEstado() != null ? req.getEstado() : "disponible");
        m.setActivo(true);

        return mesaRepository.save(m);
    }

    // Listar todas las mesas
    public List<Mesa> listarTodas() {
        return mesaRepository.findAll();
    }

    // Listar mesas por sucursal
    public List<Mesa> listarPorSucursal(Integer sucursalId) {
        return mesaRepository.findBySucursalId(sucursalId);
    }

    // Obtener mesa por ID
    public Mesa obtenerPorId(Integer id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
    }

    // Actualizar mesa
    public Mesa actualizar(Integer id, CreateMesaRequest req) {

        Mesa m = obtenerPorId(id);

        if (req.getCapacidad() != null) m.setCapacidad(req.getCapacidad());
        if (req.getNumero() != null) m.setNumero(req.getNumero());
        if (req.getEstado() != null) m.setEstado(req.getEstado());

        if (req.getIdSucursal() != null) {
            Sucursal s = sucursalRepository.findById(req.getIdSucursal())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
            m.setSucursal(s);
        }

        return mesaRepository.save(m);
    }

    // Cambiar estado (reservada, ocupada, disponible)
    public Mesa cambiarEstado(Integer id, String nuevoEstado) {
        Mesa m = obtenerPorId(id);
        m.setEstado(nuevoEstado);
        return mesaRepository.save(m);
    }

    // Soft delete
    public void eliminar(Integer id) {
        Mesa m = obtenerPorId(id);
        m.setActivo(false);
        mesaRepository.save(m);
    }
}
