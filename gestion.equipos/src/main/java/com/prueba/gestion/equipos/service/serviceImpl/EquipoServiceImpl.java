package com.prueba.gestion.equipos.service.serviceImpl;

import com.prueba.gestion.equipos.exception.ResourceNotFoundException;
import com.prueba.gestion.equipos.model.Equipo;
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
    public List<Equipo> findAllEquipos() throws ResourceNotFoundException {
        List<Equipo> equipoList = equipoRepository.findAll();
        if(equipoList.isEmpty()){
            throw new ResourceNotFoundException("noDataFound", "");
        }

        return equipoList;
    }
    @Override
    public Optional<Equipo> findEquipoById(Long id){
        Optional<Equipo> objEquipo = equipoRepository.findById(id);
        return objEquipo;
    }
    @Override
    public Equipo createEquipo(Equipo equipo) throws Exception {
        if (equipo == null){
            throw new Exception("Datos incorrectos");
        }
        Equipo objEquipo = new Equipo();
        objEquipo.setNombre(equipo.getNombre());
        objEquipo.setLiga(equipo.getLiga());
        objEquipo.setPais(equipo.getPais());
        equipoRepository.save(objEquipo);
        return objEquipo;
    }
    @Override
    public void deleteEquipo(Long id){
        equipoRepository.deleteById(id);
    }
}

