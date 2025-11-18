package com.universidad.backend.service;

import com.universidad.backend.dto.CreateClienteRequest;
import com.universidad.backend.model.Cliente;
import com.universidad.backend.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Crear cliente
    public Cliente crear(CreateClienteRequest req) {

        Cliente c = new Cliente();

        c.setNombre(req.getNombre() != null ? req.getNombre() : "Invitado");
        c.setTelefono(req.getTelefono());
        c.setDireccion(req.getDireccion());
        c.setActivo(true);

        return clienteRepository.save(c);
    }

    // Listar todos
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Obtener 1
    public Cliente obtenerPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    // Actualizar
    public Cliente actualizar(Integer id, CreateClienteRequest req) {

        Cliente c = obtenerPorId(id);

        if (req.getNombre() != null)
            c.setNombre(req.getNombre());

        if (req.getTelefono() != null)
            c.setTelefono(req.getTelefono());

        if (req.getDireccion() != null)
            c.setDireccion(req.getDireccion());

        return clienteRepository.save(c);
    }

    // Soft delete
    public void eliminar(Integer id) {
        Cliente c = obtenerPorId(id);
        c.setActivo(false);
        clienteRepository.save(c);
    }
}
