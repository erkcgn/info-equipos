package com.prueba.gestion.equipos.service.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.prueba.gestion.equipos.exception.ResourceException;
import com.prueba.gestion.equipos.model.Equipo;
import com.prueba.gestion.equipos.repository.EquipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;


public class EquipoServiceImplTest {
    @InjectMocks
    private EquipoServiceImpl equipoService;
    @Mock
    private EquipoRepository equipoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        equipos.add(new Equipo(1L, "Equipo 1", "Liga 1", "País 1"));
        equipos.add(new Equipo(2L, "Equipo 2", "Liga 2", "País 2"));

        when(equipoRepository.findAll()).thenReturn(equipos);

        List<Equipo> equiposEncontrados = equipoService.findAllEquipos();

        assertEquals(equipos, equiposEncontrados);
    }

    @Test
    public void testFindEquipoById() {
        Long equipoId = 1L;
        String equipoNombre = "Equipo";
        String equipoLiga = "Liga";
        String equipoPais = "Pais";

        Equipo equipoEnDB = new Equipo();
        equipoEnDB.setId(equipoId);
        equipoEnDB.setNombre(equipoNombre);
        equipoEnDB.setLiga(equipoLiga);
        equipoEnDB.setPais(equipoPais);

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipoEnDB));

        Optional<Equipo> resultado = equipoService.findEquipoById(equipoId);

        assertTrue(resultado.isPresent());
        assertEquals(equipoId, resultado.get().getId());
        assertEquals(equipoNombre, resultado.get().getNombre());
        assertEquals(equipoLiga, resultado.get().getLiga());
        assertEquals(equipoPais, resultado.get().getPais());
        verify(equipoRepository, times(1)).findById(equipoId);
    }

    @Test
    public void testFindEquiposByNombre() {
        String nombreEquipo = "Equipo";

        List<Equipo> equiposSimulados = new ArrayList<>();
        equiposSimulados.add(new Equipo(1L, nombreEquipo, "Liga1", "Pais1"));
        equiposSimulados.add(new Equipo(2L, nombreEquipo, "Liga2", "Pais2"));
        equiposSimulados.add(new Equipo(3L, nombreEquipo, "Liga3", "Pais3"));

        when(equipoRepository.findByNombreContaining(nombreEquipo)).thenReturn(equiposSimulados);

        List<Equipo> resultados = equipoService.findEquiposByNombre(nombreEquipo);

        assertFalse(resultados.isEmpty());
        assertEquals(3, resultados.size());
        verify(equipoRepository, times(1)).findByNombreContaining(nombreEquipo);
    }

    @Test
    public void testCreateEquipo() {
        Equipo equipo = new Equipo();
        equipo.setNombre("Firulais");
        equipo.setLiga("La Tormenta");
        equipo.setPais("Australia");

        when(equipoRepository.findByNombre("Firulais")).thenReturn(null);
        when(equipoRepository.save(any(Equipo.class))).thenReturn(equipo);

        Equipo createdEquipo = equipoService.createEquipo(equipo);

        assertNotNull(createdEquipo);
        assertEquals("Firulais", createdEquipo.getNombre());
        assertEquals("La Tormenta", createdEquipo.getLiga());
        assertEquals("Australia", createdEquipo.getPais());
        verify(equipoRepository, times(1)).save(any(Equipo.class));
    }

    @Test
    public void testCreateEquipoConNombreExistente() {
        Equipo equipo = new Equipo();
        equipo.setNombre("Firulais");
        equipo.setLiga("La Tormenta");
        equipo.setPais("Australia");

        when(equipoRepository.findByNombre("Firulais")).thenReturn(equipo);

        ResourceException exception = assertThrows(ResourceException.class, () -> {
            equipoService.createEquipo(equipo);
        });
        assertEquals("Ya existe un equipo con el nombre ingresado.", exception.getMensaje());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getCodigo());
        verify(equipoRepository, never()).save(any(Equipo.class));
    }

    @Test
    public void testUpdateEquipo() {
        Long equipoId = 1L;
        String equipoNombreExistente = "Bad Bunny";
        String equipoLigaExistente = "Amateur";
        String equipoPaisExistente = "EEUU";

        String nuevoNombre = "Pato Lucas";
        String nuevaLiga = "Lo Intentaron";
        String nuevoPais = "Nunca Jamas";

        Equipo equipoExistente = new Equipo();
        equipoExistente.setId(equipoId);
        equipoExistente.setNombre(equipoNombreExistente);
        equipoExistente.setLiga(equipoLigaExistente);
        equipoExistente.setPais(equipoPaisExistente);

        Equipo equipoActualizado = new Equipo();
        equipoActualizado.setId(equipoId);
        equipoActualizado.setNombre(nuevoNombre);
        equipoActualizado.setLiga(nuevaLiga);
        equipoActualizado.setPais(nuevoPais);

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(equipoExistente));
        when(equipoRepository.save(any(Equipo.class))).thenReturn(equipoActualizado);

        Equipo resultado = equipoService.updateEquipo(equipoId, equipoActualizado);

        assertNotNull(resultado);
        assertEquals(equipoId, resultado.getId());
        assertEquals(nuevoNombre, resultado.getNombre());
        assertEquals(nuevaLiga, resultado.getLiga());
        assertEquals(nuevoPais, resultado.getPais());
        verify(equipoRepository, times(1)).findById(equipoId);
        verify(equipoRepository, times(1)).save(any(Equipo.class));
    }

    @Test
    public void testDeleteEquipo() {
        Long equipoId = 1L;

        when(equipoRepository.findById(equipoId)).thenReturn(Optional.of(new Equipo()));

        assertDoesNotThrow(() -> equipoService.deleteEquipo(equipoId));

        verify(equipoRepository, times(1)).findById(equipoId);
        verify(equipoRepository, times(1)).deleteById(equipoId);
    }

}

