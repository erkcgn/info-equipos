package com.prueba.gestion.equipos.security.jwt;

import com.prueba.gestion.equipos.model.Persona;
import com.prueba.gestion.equipos.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    String generateToken(Persona persona);

    boolean isTokenValid(HttpServletRequest request);
}
