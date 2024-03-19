package com.prueba.gestion.equipos.service.serviceImpl;

import com.prueba.gestion.equipos.model.Persona;
import com.prueba.gestion.equipos.service.PersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Override
    public Persona crearPersona() {
        Persona objPersona = new Persona();
        objPersona.setNombre("test");
        objPersona.setPassword("12345");

        return objPersona;
    }
}
