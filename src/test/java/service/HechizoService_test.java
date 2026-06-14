package service;

import eva2.eva2.model.Hechizo;
import eva2.eva2.repository.HechizoRepository;
import eva2.eva2.service.HechizoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HechizoServiceTest {

    @Mock
    private HechizoRepository hechizoRepository;

    @InjectMocks
    private HechizoService hechizoService;

    @Test
    void testFindAll() {

        Hechizo hechizo =
                new Hechizo(1, "HECHIZO1", "atributo1", 1, true);

        when(hechizoRepository.findAll())
                .thenReturn(List.of(hechizo));

        List<Hechizo> resultado = hechizoService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("HECHIZO1", resultado.get(0).getNombre());
    }

    @Test
    void testFindById() {

        Integer id = 1;

        Hechizo hechizo =
                new Hechizo(1, "HECHIZO1", "atributo1", 1, true);

        when(hechizoRepository.findById(id))
                .thenReturn(Optional.of(hechizo));

        Hechizo resultado = hechizoService.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
    }

    @Test
    void testSave() {

        Hechizo hechizo =
                new Hechizo(1, "HECHIZO1", "atributo1", 1, true);

        when(hechizoRepository.save(hechizo))
                .thenReturn(hechizo);

        Hechizo resultado = hechizoService.save(hechizo);

        assertNotNull(resultado);
        assertEquals("HECHIZO1", resultado.getNombre());
    }

    @Test
    void testDelete() {

        Integer id = 1;

        doNothing().when(hechizoRepository).deleteById(id);

        hechizoService.delete(id);

        verify(hechizoRepository, times(1))
                .deleteById(id);
    }

    @Test
    void testFindByAtributo() {

        Hechizo hechizo =
                new Hechizo(1, "HECHIZO1", "atributo1", 1, true);

        when(hechizoRepository.findByAtributo("atributo1"))
                .thenReturn(hechizo);

        Hechizo resultado =
                hechizoService.findByAtributo("atributo1");

        assertNotNull(resultado);
        assertEquals("atributo1", resultado.getAtributo());
    }

    @Test
    void testFindByNivel() {

        Hechizo hechizo =
                new Hechizo(1, "HECHIZO1", "atributo1", 1, true);

        when(hechizoRepository.findByNivel(1))
                .thenReturn(hechizo);

        Hechizo resultado =
                hechizoService.findByNivel(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getNivel());
    }

    @Test
    void testFindByEnPos() {

        Hechizo hechizo =
                new Hechizo(1, "HECHIZO1", "atributo1", 1, true);

        when(hechizoRepository.findByEnPos(true))
                .thenReturn(hechizo);

        Hechizo resultado =
                hechizoService.findByEnPos(true);

        assertNotNull(resultado);
        assertTrue(resultado.isEnPos());
    }
}