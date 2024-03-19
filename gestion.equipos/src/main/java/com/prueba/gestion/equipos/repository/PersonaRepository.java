package com.prueba.gestion.equipos.repository;

import com.prueba.gestion.equipos.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long> {

    Persona findByNombre(String nombre);
}
