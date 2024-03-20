package com.prueba.gestion.equipos.service.serviceImpl;

import com.prueba.gestion.equipos.model.Persona;
import com.prueba.gestion.equipos.repository.PersonaRepository;
import com.prueba.gestion.equipos.service.PersonaService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    public PersonaServiceImpl(PersonaRepository personaRepository, PasswordEncoder passwordEncoder) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void crearPersona() {

        Persona personaExistente = personaRepository.findByUsername("test");
        if (personaExistente == null) {
            Persona objPersona = new Persona();
            objPersona.setUsername("test");
            objPersona.setPassword(passwordEncoder.encode("12345"));
            personaRepository.save(objPersona);
        }
    }
    @Override
    public Optional<Persona> findByUsername(String username){
        return Optional.ofNullable(personaRepository.findByUsername(username));
    }
}
