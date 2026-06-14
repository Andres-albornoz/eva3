package eva2.eva2.service;

import eva2.eva2.model.Mago;
import eva2.eva2.repository.MagoRepository;
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
class MagoServiceTest {

    @Mock
    private MagoRepository magoRepository;

    @InjectMocks
    private MagoService magoService;

    @Test
    void testFindAll() {
        when(magoRepository.findAll()).thenReturn(List.of(new Mago()));
        assertEquals(1, magoService.findAll().size());
    }

    @Test
    void testFindById() {
        when(magoRepository.findById(1))
                .thenReturn(Optional.of(new Mago()));

        assertNotNull(magoService.findById(1));
    }

    @Test
    void testSave() {

        Mago mago = new Mago();

        when(magoRepository.save(mago))
                .thenReturn(mago);

        assertNotNull(magoService.save(mago));
    }

    @Test
    void testDelete() {
        magoService.delete(1);
        verify(magoRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {

        when(magoRepository.findByNombre("Merlin"))
                .thenReturn(new Mago());

        assertNotNull(
                magoService.findByNombre("Merlin")
        );
    }

    @Test
    void testFindByNivel() {

        when(magoRepository.findByNivel(5))
                .thenReturn(new Mago());

        assertNotNull(
                magoService.findByNivel(5)
        );
    }

    @Test
    void testFindByMana() {

        when(magoRepository.findByMana(100))
                .thenReturn(new Mago());

        assertNotNull(
                magoService.findByMana(100)
        );
    }
}