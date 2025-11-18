package com.universidad.backend.service;

import com.universidad.backend.dto.CreateReservaRequest;
import com.universidad.backend.model.*;
import com.universidad.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final MesaRepository mesaRepository;
    private final ReservaMesaRepository reservaMesaRepository;

    @Transactional
    public Reserva crear(CreateReservaRequest req) {
        Cliente cliente = clienteRepository.findById(req.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // crear reserva primero
        Reserva r = new Reserva();
        r.setCliente(cliente);
        r.setFechaHora(req.getFechaHora());
        r.setObservaciones(req.getObservaciones());
        r.setActivo(true);
        r = reservaRepository.save(r);

        // asociar mesas en la tabla intermedia
        if (req.getIdMesas() != null && !req.getIdMesas().isEmpty()) {
            List<Mesa> mesas = mesaRepository.findAllById(req.getIdMesas());
            for (Mesa m : mesas) {
                ReservaMesa rm = new ReservaMesa();
                rm.setIdReserva(r.getId());
                rm.setIdMesa(m.getId());
                rm.setReserva(r);
                rm.setMesa(m);
                reservaMesaRepository.save(rm);
                r.getReservaMesas().add(rm);
            }
        }

        return r;
    }

    public List<Reserva> listar() {
        return reservaRepository.findAll();
    }

    public List<Reserva> listarPorCliente(Integer idCliente) {
        return reservaRepository.findByClienteId(idCliente);
    }

    public Reserva obtener(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    public List<Reserva> listarPorFecha(LocalDateTime inicio, LocalDateTime fin) {
        return reservaRepository.findByFechaHoraBetween(inicio, fin);
    }

    @Transactional
    public Reserva actualizar(Integer id, CreateReservaRequest req) {
        Reserva r = obtener(id);

        if (req.getFechaHora() != null) r.setFechaHora(req.getFechaHora());
        if (req.getObservaciones() != null) r.setObservaciones(req.getObservaciones());

        // Si vienen idMesas: eliminar relaciones previas y crear nuevas
        if (req.getIdMesas() != null) {
            // borrar filas intermedias existentes para esta reserva
            reservaMesaRepository.deleteByIdReserva(r.getId());
            r.getReservaMesas().clear();

            List<Mesa> mesas = mesaRepository.findAllById(req.getIdMesas());
            for (Mesa m : mesas) {
                ReservaMesa rm = new ReservaMesa();
                rm.setIdReserva(r.getId());
                rm.setIdMesa(m.getId());
                rm.setReserva(r);
                rm.setMesa(m);
                reservaMesaRepository.save(rm);
                r.getReservaMesas().add(rm);
            }
        }

        return reservaRepository.save(r);
    }

    public void cancelar(Integer id) {
        Reserva r = obtener(id);
        r.setActivo(false);
        reservaRepository.save(r);
    }
}
