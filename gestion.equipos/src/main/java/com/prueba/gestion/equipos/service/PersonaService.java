package com.prueba.gestion.equipos.service;

import com.prueba.gestion.equipos.model.Persona;

import java.util.Optional;

public interface PersonaService {
    void crearPersona();

   Optional<Persona> findByUsername(String username);
}
