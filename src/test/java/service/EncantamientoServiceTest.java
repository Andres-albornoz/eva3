package eva2.eva2.service;

import eva2.eva2.model.Encantamiento;
import eva2.eva2.repository.EncantamientoRepository;
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
class EncantamientoServiceTest {

    @Mock
    private EncantamientoRepository encantamientoRepository;

    @InjectMocks
    private EncantamientoService encantamientoService;

    @Test
    void testFindAll() {
        when(encantamientoRepository.findAll()).thenReturn(List.of(new Encantamiento()));
        assertEquals(1, encantamientoService.findAll().size());
    }

    @Test
    void testFindById() {
        when(encantamientoRepository.findById(1))
                .thenReturn(Optional.of(new Encantamiento()));

        assertNotNull(encantamientoService.findById(1));
    }

    @Test
    void testSave() {
        Encantamiento encantamiento = new Encantamiento();

        when(encantamientoRepository.save(encantamiento))
                .thenReturn(encantamiento);

        assertNotNull(encantamientoService.save(encantamiento));
    }

    @Test
    void testDelete() {
        encantamientoService.delete(1);
        verify(encantamientoRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {

        when(encantamientoRepository.findByNombre("Bendición"))
                .thenReturn(new Encantamiento());

        assertNotNull(
                encantamientoService.findByNombre("Bendición")
        );
    }

    @Test
    void testFindByNivel() {

        when(encantamientoRepository.findByNivel(5))
                .thenReturn(new Encantamiento());

        assertNotNull(
                encantamientoService.findByNivel(5)
        );
    }
}