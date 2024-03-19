package com.prueba.gestion.equipos.repository;

import com.prueba.gestion.equipos.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo> findAll();

    Optional<Equipo> findById(Long id);

}

