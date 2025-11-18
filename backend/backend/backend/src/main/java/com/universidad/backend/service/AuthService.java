package com.universidad.backend.service;

import com.universidad.backend.dto.*;
import com.universidad.backend.model.*;
import com.universidad.backend.repository.*;
import com.universidad.backend.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(RegisterRequest req) {
        if (usuarioRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email ya registrado");
        }

        Rol rol = rolRepository.findByNombre(req.getRol())
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "nombre", req.getRol()));

        Usuario usuario = new Usuario();
        usuario.setNombre(req.getNombre());
        usuario.setEmail(req.getEmail());
        usuario.setContrasena(passwordEncoder.encode(req.getContrasena()));
        usuario.setRol(rol);
        usuario.setActivo(true);

        usuarioRepository.save(usuario);
    }

    public LoginResponse login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getContrasena())
        );

        Usuario usuario = usuarioRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "email", req.getEmail()));

        String token = jwtService.generateToken(usuario.getEmail(),
                Map.of("rol", usuario.getRol().getNombre(), "nombre", usuario.getNombre()));

        return new LoginResponse(token, usuario.getNombre(), usuario.getRol().getNombre());
    }
}
