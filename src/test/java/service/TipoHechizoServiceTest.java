package eva2.eva2.service;

import eva2.eva2.model.TipoHechizo;
import eva2.eva2.repository.TipoHechizoRepository;
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
class TipoHechizoServiceTest {

    @Mock
    private TipoHechizoRepository tipoHechizoRepository;

    @InjectMocks
    private TipoHechizoService tipoHechizoService;

    @Test
    void testFindAll() {
        when(tipoHechizoRepository.findAll()).thenReturn(List.of(new TipoHechizo()));
        assertEquals(1, tipoHechizoService.findAll().size());
    }

    @Test
    void testFindById() {
        when(tipoHechizoRepository.findById(1)).thenReturn(Optional.of(new TipoHechizo()));
        assertNotNull(tipoHechizoService.findById(1));
    }

    @Test
    void testSave() {
        TipoHechizo t = new TipoHechizo();
        when(tipoHechizoRepository.save(t)).thenReturn(t);
        assertNotNull(tipoHechizoService.save(t));
    }

    @Test
    void testDelete() {
        tipoHechizoService.delete(1);
        verify(tipoHechizoRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {
        when(tipoHechizoRepository.findByNombre("Ataque"))
                .thenReturn(new TipoHechizo());

        assertNotNull(tipoHechizoService.findByNombre("Ataque"));
    }
}