package com.prueba.gestion.equipos.controller;

import com.prueba.gestion.equipos.model.Equipo;
import org.springframework.http.HttpStatus;
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
        try {
            List<Equipo> equipoList = equipoService.findAllEquipos();
            return ResponseEntity.ok(equipoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/equipos/{id}")
    public ResponseEntity<Optional<Equipo>> getEquipoById(@PathVariable("id") Long id){
        Optional<Equipo> objEquipo = equipoService.findEquipoById(id);
        return ResponseEntity.ok(objEquipo);
    }
    @PostMapping("/equipos")
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo){
        try {
            Equipo objEquipo = equipoService.createEquipo(equipo);
            return new ResponseEntity<>(objEquipo, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/equipos/{id}")
    public void deleteEquipoById(@PathVariable("id") Long id){
        equipoService.deleteEquipo(id);
    }

}

