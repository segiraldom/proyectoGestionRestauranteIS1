package com.universidad.backend.service;

import com.universidad.backend.model.Restaurante;
import com.universidad.backend.repository.RestauranteRepository;
import com.universidad.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public Restaurante crear(Restaurante r) {
        if (r.getNombre() == null || r.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del restaurante es obligatorio");
        }
        if (r.getNit() == null || r.getNit().isBlank()) {
            throw new IllegalArgumentException("El NIT es obligatorio");
        }
        if (restauranteRepository.existsByNombre(r.getNombre())) {
            throw new IllegalArgumentException("El nombre del restaurante ya está en uso");
        }
        if (restauranteRepository.existsByNit(r.getNit())) {
            throw new IllegalArgumentException("El NIT ya está registrado");
        }
        return restauranteRepository.save(r);
    }

    public Restaurante obtenerPorId(Integer id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurante", "id", id));
    }

    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    public Restaurante actualizar(Integer id, Restaurante datos) {
        Restaurante r = obtenerPorId(id);
        if (datos.getNombre() != null && !datos.getNombre().isBlank()) r.setNombre(datos.getNombre());
        if (datos.getNit() != null && !datos.getNit().isBlank()) r.setNit(datos.getNit());
        return restauranteRepository.save(r);
    }

    public void eliminar(Integer id) {
        Restaurante r = obtenerPorId(id);
        restauranteRepository.delete(r);
    }
}
