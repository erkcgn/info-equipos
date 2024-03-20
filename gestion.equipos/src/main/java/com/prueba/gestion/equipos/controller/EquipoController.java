package com.prueba.gestion.equipos.controller;

import com.prueba.gestion.equipos.exception.ResourceException;
import com.prueba.gestion.equipos.model.Equipo;
import com.prueba.gestion.equipos.rest.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Consulta de Todos los Equipos", description = "Devuelve la lista de todos los equipos de fútbol registrados.")
    @GetMapping("/equipos")
    public ResponseEntity<List<Equipo>> getAllEquipos(){
        List<Equipo> equipoList = equipoService.findAllEquipos();
        return ResponseEntity.ok(equipoList);
    }

    @Operation(summary = "Consulta de un Equipo por ID", description = "Devuelve la información del equipo correspondiente al ID proporcionado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la información del equipo correspondiente al ID proporcionado."),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @GetMapping("/equipos/{id}")
    public ResponseEntity<GenericResponse<Equipo>> getEquipoById(@PathVariable("id") Long id) {
        try {
            Optional<Equipo> objEquipo = equipoService.findEquipoById(id);
            return GenericResponse.okResponse(objEquipo.get());
        } catch (ResourceException ex) {
            return GenericResponse.exceptionResponse(ex);
        }
    }

    @Operation(summary = "Búsqueda de Equipos por Nombre", description = "Devuelve la lista de equipos cuyos nombres contienen el valor proporcionado en el parámetro de búsqueda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipos encontrados")
    })
    @GetMapping("/equipos/buscar")
    public ResponseEntity<GenericResponse<List<Equipo>>> getEquiposByNombre(@RequestParam("nombre") String nombre) {
        try {
            List<Equipo> equipoList = equipoService.findEquiposByNombre(nombre);
            return GenericResponse.okResponse(equipoList);
        } catch (ResourceException ex) {
            return GenericResponse.exceptionResponse(ex);
        }
    }
    @Operation(summary = "Creación de un equipo", description = "Datos del nuevo equipo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Datos del nuevo equipo"),
            @ApiResponse(responseCode = "400", description = "La solicitud es invalida")
    })
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
    @Operation(summary = "Actualización de Información de un Equipo", description = "Datos actualizados del equipo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devuelve la información actualizada del equipo"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
    @PutMapping("/equipos/{id}")
    public ResponseEntity<GenericResponse<Equipo>> updateEquipoById(@PathVariable("id") Long id, @RequestBody Equipo equipo) {
        try {
            Equipo equipoActualizado = equipoService.updateEquipo(id, equipo);
            return GenericResponse.okResponse(equipoActualizado);
        } catch (ResourceException ex) {
            return GenericResponse.exceptionResponse(ex);
        }
    }
    @Operation(summary = "Eliminación de un Equipo", description = "Elimina un equipo por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sin contenid"),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado")
    })
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

