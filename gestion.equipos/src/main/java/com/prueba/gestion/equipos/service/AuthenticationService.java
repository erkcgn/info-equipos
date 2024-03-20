package com.prueba.gestion.equipos.service;

import com.prueba.gestion.equipos.model.Persona;

public interface AuthenticationService {
    Persona signInAndReturnJWT(Persona signInRequest);
}
