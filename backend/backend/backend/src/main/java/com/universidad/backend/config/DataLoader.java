package com.universidad.backend.config;

import com.universidad.backend.model.Rol;
import com.universidad.backend.repository.RolRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final RolRepository rolRepository;

    @PostConstruct
    public void init() {
        crearRolSiNoExiste("ADMIN","Administrador del sistema");
        crearRolSiNoExiste("GERENTE","Gerente de sucursal");
        crearRolSiNoExiste("MESERO","Empleado - Mesero");
        crearRolSiNoExiste("CLIENTE","Cliente consumidor");
    }

    private void crearRolSiNoExiste(String nombre, String descripcion) {
        rolRepository.findByNombre(nombre).orElseGet(() -> {
            Rol r = new Rol();
            r.setNombre(nombre);
            r.setDescripcion(descripcion);
            return rolRepository.save(r);
        });
    }
}
