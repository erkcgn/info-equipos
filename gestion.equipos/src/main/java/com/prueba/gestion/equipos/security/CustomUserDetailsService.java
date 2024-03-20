package com.prueba.gestion.equipos.security;

import com.prueba.gestion.equipos.model.Persona;
import com.prueba.gestion.equipos.service.PersonaService;
import com.prueba.gestion.equipos.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PersonaService personaService;

    public CustomUserDetailsService(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Persona persona = personaService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado: " + username));

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(username));

        return UserPrincipal.builder()
                .persona(persona)
                .id(persona.getId())
                .username(username)
                .password(persona.getPassword())
                .authorities(authorities)
                .build();
    }
}
