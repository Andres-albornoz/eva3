package eva2.eva2.service;

import eva2.eva2.model.EscuelaMagica;
import eva2.eva2.repository.EscuelaMagicaRepository;
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
class EscuelaMagicaServiceTest {

    @Mock
    private EscuelaMagicaRepository escuelaMagicaRepository;

    @InjectMocks
    private EscuelaMagicaService escuelaMagicaService;

    @Test
    void testFindAll() {
        when(escuelaMagicaRepository.findAll()).thenReturn(List.of(new EscuelaMagica()));
        assertEquals(1, escuelaMagicaService.findAll().size());
    }

    @Test
    void testFindById() {
        when(escuelaMagicaRepository.findById(1)).thenReturn(Optional.of(new EscuelaMagica()));
        assertNotNull(escuelaMagicaService.findById(1));
    }

    @Test
    void testSave() {
        EscuelaMagica e = new EscuelaMagica();
        when(escuelaMagicaRepository.save(e)).thenReturn(e);
        assertNotNull(escuelaMagicaService.save(e));
    }

    @Test
    void testDelete() {
        escuelaMagicaService.delete(1);
        verify(escuelaMagicaRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {
        when(escuelaMagicaRepository.findByNombre("Arcana"))
                .thenReturn(new EscuelaMagica());

        assertNotNull(escuelaMagicaService.findByNombre("Arcana"));
    }

    @Test
    void testFindByEspecialidad() {
        when(escuelaMagicaRepository.findByEspecialidad("Fuego"))
                .thenReturn(new EscuelaMagica());

        assertNotNull(escuelaMagicaService.findByEspecialidad("Fuego"));
    }
}