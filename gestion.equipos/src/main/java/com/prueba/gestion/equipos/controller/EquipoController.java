package com.prueba.gestion.equipos.controller;

import com.prueba.gestion.equipos.exception.ResourceException;
import com.prueba.gestion.equipos.model.Equipo;
import com.prueba.gestion.equipos.rest.GenericResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prueba.gestion.equipos.service.EquipoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getAllEquipos(){
        List<Equipo> equipoList = equipoService.findAllEquipos();
        return ResponseEntity.ok(equipoList);
    }
    @GetMapping("/equipos/{id}")
    public ResponseEntity<GenericResponse<Equipo>> getEquipoById(@PathVariable("id") Long id) {
        try {
            Optional<Equipo> objEquipo = equipoService.findEquipoById(id);
            return GenericResponse.okResponse(objEquipo.get());
        } catch (ResourceException ex) {
            return GenericResponse.exceptionResponse(ex);
        }
    }

    @GetMapping("/equipos/buscar")
    public ResponseEntity<GenericResponse<List<Equipo>>> findEquiposByNombre(@RequestParam("nombre") String nombre) {
        try {
            List<Equipo> equipoList = equipoService.findEquiposByNombre(nombre);
            System.out.println(equipoList);
            return GenericResponse.okResponse(equipoList);
        } catch (ResourceException ex) {
            return GenericResponse.exceptionResponse(ex);
        }
    }

    @PostMapping("/equipos")
    public ResponseEntity<GenericResponse<Equipo>> createEquipo(@RequestBody @NotNull Equipo equipo) {
        try {
            Equipo objEquipo = equipoService.createEquipo(equipo);
            return GenericResponse.createdResponse(objEquipo);
        } catch (ResourceException ex) {
            return GenericResponse.exceptionResponse(ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/equipos/{id}")
    public ResponseEntity<GenericResponse<Equipo>> updateEquipoById(@PathVariable("id") Long id, @RequestBody Equipo equipo) {
        try {
            Equipo equipoActualizado = equipoService.updateEquipo(id, equipo);
            return GenericResponse.okResponse(equipoActualizado);
        } catch (ResourceException ex) {
            return GenericResponse.exceptionResponse(ex);
        }
    }

    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteEquipoById(@PathVariable("id") Long id) throws ResourceException {
        try {
            equipoService.deleteEquipo(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceException ex) {
            return GenericResponse.exceptionResponse(ex);
        }
    }

}

