package com.prueba.gestion.equipos.config;

import com.prueba.gestion.equipos.service.PersonaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitializer implements ApplicationRunner {

    private final PersonaService personaService;

    public ApplicationInitializer(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        personaService.crearPersona();
    }
}

