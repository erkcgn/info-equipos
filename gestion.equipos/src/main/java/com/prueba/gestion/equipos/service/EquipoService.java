package com.prueba.gestion.equipos.service;

import com.prueba.gestion.equipos.exception.ResourceNotFoundException;
import com.prueba.gestion.equipos.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    List<Equipo> findAllEquipos() throws ResourceNotFoundException;

    Optional<Equipo> findEquipoById(Long equipoId);

    Equipo createEquipo(Equipo equipo) throws Exception;

    void deleteEquipo(Long id);
}
