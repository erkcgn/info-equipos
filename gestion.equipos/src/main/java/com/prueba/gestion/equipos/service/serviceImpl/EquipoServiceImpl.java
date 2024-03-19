package com.prueba.gestion.equipos.service.serviceImpl;

import com.prueba.gestion.equipos.exception.ResourceException;
import com.prueba.gestion.equipos.model.Equipo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.prueba.gestion.equipos.repository.EquipoRepository;
import com.prueba.gestion.equipos.service.EquipoService;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public List<Equipo> findAllEquipos() {
        List<Equipo> equipoList = equipoRepository.findAll();
        return equipoList;
    }

    @Override
    public Optional<Equipo> findEquipoById(Long id){
        Optional<Equipo> objEquipo = equipoRepository.findById(id);
        if (objEquipo.isEmpty()){
            throw new ResourceException("Equipo no encontrado", HttpStatus.NOT_FOUND.value());
        }
        return objEquipo;
    }
    @Override
    public List<Equipo> findEquiposByNombre(String nombre) {
        return equipoRepository.findByNombreContaining(nombre);
    }

    @Override
    public Equipo createEquipo(Equipo equipo) {

        if (equipo == null ||
                equipo.getNombre() == null || !equipo.getNombre().matches("^[a-zA-Z\\s]+$") ||
                equipo.getLiga() == null || !equipo.getLiga().matches("^[a-zA-Z\\s]+$") ||
                equipo.getPais() == null || !equipo.getPais().matches("^[a-zA-Z\\s]+$")) {
            throw new ResourceException("La solicitud es invalida", HttpStatus.BAD_REQUEST.value());
        }

        if (equipoRepository.findByNombre(equipo.getNombre()) != null) {
            throw new ResourceException("Ya existe un equipo con el nombre ingresado.", HttpStatus.BAD_REQUEST.value());
        }

        Equipo objEquipo = new Equipo();
        objEquipo.setNombre(equipo.getNombre());
        objEquipo.setLiga(equipo.getLiga());
        objEquipo.setPais(equipo.getPais());
        equipoRepository.save(objEquipo);

        return objEquipo;
    }
    @Override
    public Equipo updateEquipo(Long id, Equipo equipo) {
        Optional<Equipo> equipoActual = equipoRepository.findById(id);
        if (equipoActual.isEmpty()) {
            throw new ResourceException("Equipo no encontrado", HttpStatus.NOT_FOUND.value());
        }

        if (equipo == null ||
                equipo.getNombre() == null || !equipo.getNombre().matches("^[a-zA-Z\\s]+$") ||
                equipo.getLiga() == null || !equipo.getLiga().matches("^[a-zA-Z\\s]+$") ||
                equipo.getPais() == null || !equipo.getPais().matches("^[a-zA-Z\\s]+$"))  {
            throw new ResourceException("La solicitud es invalida", HttpStatus.BAD_REQUEST.value());
        }

        String nuevoNombre = equipo.getNombre();
        Optional<Equipo> optionalEquipoExistente = Optional.ofNullable(equipoRepository.findByNombre(nuevoNombre));
        if (optionalEquipoExistente.isPresent() && !optionalEquipoExistente.get().getId().equals(id)) {
            throw new ResourceException("Ya existe un equipo con el nombre ingresado.", HttpStatus.BAD_REQUEST.value());
        }

        Equipo equipoExistente = equipoActual.get();
        equipoExistente.setNombre(equipo.getNombre());
        equipoExistente.setLiga(equipo.getLiga());
        equipoExistente.setPais(equipo.getPais());
        return equipoRepository.save(equipoExistente);
    }

    @Override
    public void deleteEquipo(Long id) throws ResourceException {
        Optional<Equipo> equipoOptional = equipoRepository.findById(id);
        if (equipoOptional.isPresent()) {
            equipoRepository.deleteById(id);
        } else {
            throw new ResourceException("Equipo no encontrado", HttpStatus.NOT_FOUND.value());
        }
    }
}

