package eva2.eva2.service;

import eva2.eva2.model.Efecto;
import eva2.eva2.repository.EfectoRepository;
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
class EfectoServiceTest {

    @Mock
    private EfectoRepository efectoRepository;

    @InjectMocks
    private EfectoService efectoService;

    @Test
    void testFindAll() {
        when(efectoRepository.findAll()).thenReturn(List.of(new Efecto()));
        assertEquals(1, efectoService.findAll().size());
    }

    @Test
    void testFindById() {
        when(efectoRepository.findById(1))
                .thenReturn(Optional.of(new Efecto()));

        assertNotNull(efectoService.findById(1));
    }

    @Test
    void testSave() {
        Efecto efecto = new Efecto();

        when(efectoRepository.save(efecto))
                .thenReturn(efecto);

        assertNotNull(efectoService.save(efecto));
    }

    @Test
    void testDelete() {
        efectoService.delete(1);
        verify(efectoRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {

        when(efectoRepository.findByNombre("Quemadura"))
                .thenReturn(new Efecto());

        assertNotNull(
                efectoService.findByNombre("Quemadura")
        );
    }

    @Test
    void testFindByPotencia() {

        when(efectoRepository.findByPotencia(50))
                .thenReturn(new Efecto());

        assertNotNull(
                efectoService.findByPotencia(50)
        );
    }
}