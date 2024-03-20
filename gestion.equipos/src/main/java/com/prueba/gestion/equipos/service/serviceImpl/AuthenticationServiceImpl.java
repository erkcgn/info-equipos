package com.prueba.gestion.equipos.service.serviceImpl;

import com.prueba.gestion.equipos.model.Persona;
import com.prueba.gestion.equipos.security.UserPrincipal;
import com.prueba.gestion.equipos.security.jwt.JwtProvider;
import com.prueba.gestion.equipos.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Persona signInAndReturnJWT(Persona signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                        signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);
        Persona signInUser = userPrincipal.getPersona();
        signInUser.setToken(jwt);
        return signInUser;
    }
}
