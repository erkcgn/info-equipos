package com.prueba.gestion.equipos.service;

import com.prueba.gestion.equipos.exception.ResourceException;
import com.prueba.gestion.equipos.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    List<Equipo> findAllEquipos();

    Optional<Equipo> findEquipoById(Long equipoId);

    List<Equipo> findEquiposByNombre(String nombre);

    Equipo createEquipo(Equipo equipo) throws Exception;

    Equipo updateEquipo(Long id, Equipo equipo);

    void deleteEquipo(Long id) throws ResourceException;
}
