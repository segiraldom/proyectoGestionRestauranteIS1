package com.universidad.backend.security;

import com.universidad.backend.model.Usuario;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()));
    }

    @Override
    public String getPassword() {
        return usuario.getContrasena();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() { return usuario.getActivo() == null ? true : usuario.getActivo(); }

    @Override
    public boolean isAccountNonLocked() { return usuario.getActivo() == null ? true : usuario.getActivo(); }

    @Override
    public boolean isCredentialsNonExpired() { return usuario.getActivo() == null ? true : usuario.getActivo(); }

    @Override
    public boolean isEnabled() { return usuario.getActivo() == null ? true : usuario.getActivo(); }
}
