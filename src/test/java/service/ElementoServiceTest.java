package eva2.eva2.service;

import eva2.eva2.model.Elemento;
import eva2.eva2.repository.ElementoRepository;
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
class ElementoServiceTest {

    @Mock
    private ElementoRepository elementoRepository;

    @InjectMocks
    private ElementoService elementoService;

    @Test
    void testFindAll() {
        when(elementoRepository.findAll()).thenReturn(List.of(new Elemento()));
        assertEquals(1, elementoService.findAll().size());
    }

    @Test
    void testFindById() {
        when(elementoRepository.findById(1)).thenReturn(Optional.of(new Elemento()));
        assertNotNull(elementoService.findById(1));
    }

    @Test
    void testSave() {
        Elemento e = new Elemento();
        when(elementoRepository.save(e)).thenReturn(e);
        assertNotNull(elementoService.save(e));
    }

    @Test
    void testDelete() {
        elementoService.delete(1);
        verify(elementoRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {
        when(elementoRepository.findByNombre("Fuego"))
                .thenReturn(new Elemento());

        assertNotNull(elementoService.findByNombre("Fuego"));
    }

    @Test
    void testFindByDescripcion() {
        when(elementoRepository.findByDescripcion("Elemento"))
                .thenReturn(new Elemento());

        assertNotNull(elementoService.findByDescripcion("Elemento"));
    }
}