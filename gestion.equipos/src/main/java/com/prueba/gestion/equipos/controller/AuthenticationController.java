package com.prueba.gestion.equipos.controller;

import com.prueba.gestion.equipos.model.Persona;
import com.prueba.gestion.equipos.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Persona> signIn(@Valid @RequestBody Persona persona) {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(persona), HttpStatus.OK);
    }
}
