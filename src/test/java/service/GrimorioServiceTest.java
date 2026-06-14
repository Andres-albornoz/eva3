package eva2.eva2.service;

import eva2.eva2.model.Grimorio;
import eva2.eva2.repository.GrimorioRepository;
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
class GrimorioServiceTest {

    @Mock
    private GrimorioRepository grimorioRepository;

    @InjectMocks
    private GrimorioService grimorioService;

    @Test
    void testFindAll() {
        when(grimorioRepository.findAll()).thenReturn(List.of(new Grimorio()));
        assertEquals(1, grimorioService.findAll().size());
    }

    @Test
    void testFindById() {
        when(grimorioRepository.findById(1)).thenReturn(Optional.of(new Grimorio()));
        assertNotNull(grimorioService.findById(1));
    }

    @Test
    void testSave() {
        Grimorio g = new Grimorio();
        when(grimorioRepository.save(g)).thenReturn(g);
        assertNotNull(grimorioService.save(g));
    }

    @Test
    void testDelete() {
        grimorioService.delete(1);
        verify(grimorioRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {
        when(grimorioRepository.findByNombre("Libro"))
                .thenReturn(new Grimorio());

        assertNotNull(grimorioService.findByNombre("Libro"));
    }

    @Test
    void testFindByCapacidad() {
        when(grimorioRepository.findByCapacidad(10))
                .thenReturn(new Grimorio());

        assertNotNull(grimorioService.findByCapacidad(10));
    }
}