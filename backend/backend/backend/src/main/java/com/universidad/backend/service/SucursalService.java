package com.universidad.backend.service;

import com.universidad.backend.model.*;
import com.universidad.backend.repository.*;
import com.universidad.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final RestauranteRepository restauranteRepository;

    public Sucursal crear(Integer idRestaurante, Sucursal datos) {
        Restaurante restaurante = restauranteRepository.findById(idRestaurante)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante", "id", idRestaurante));

        Sucursal s = new Sucursal();
        s.setRestaurante(restaurante);
        s.setNombre(datos.getNombre());
        s.setDireccion(datos.getDireccion());
        s.setTelefono(datos.getTelefono());
        return sucursalRepository.save(s);
    }

    public List<Sucursal> listAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal getById(Integer id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sucursal", "id", id));
    }

    public List<Sucursal> listByRestauranteId(Integer restauranteId) {
        Restaurante rest = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante", "id", restauranteId));
        return sucursalRepository.findByRestaurante(rest);
    }

    public Sucursal update(Integer id, Sucursal datos) {
        Sucursal s = getById(id);
        if (datos.getNombre() != null) s.setNombre(datos.getNombre());
        if (datos.getDireccion() != null) s.setDireccion(datos.getDireccion());
        if (datos.getTelefono() != null) s.setTelefono(datos.getTelefono());
        return sucursalRepository.save(s);
    }

    public void delete(Integer id) {
        Sucursal s = getById(id);
        sucursalRepository.delete(s);
    }
}
