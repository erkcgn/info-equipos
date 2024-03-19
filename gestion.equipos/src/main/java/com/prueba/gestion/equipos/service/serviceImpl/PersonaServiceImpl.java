package com.prueba.gestion.equipos.service.serviceImpl;

import com.prueba.gestion.equipos.model.Persona;
import com.prueba.gestion.equipos.repository.PersonaRepository;
import com.prueba.gestion.equipos.service.PersonaService;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public void crearPersona() {

        Persona personaExistente = personaRepository.findByNombre("test");
        if (personaExistente == null) {
            Persona objPersona = new Persona();
            objPersona.setNombre("test");
            objPersona.setPassword("12345");
            personaRepository.save(objPersona);
        }
    }
}
