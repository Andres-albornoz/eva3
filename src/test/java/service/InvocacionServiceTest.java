package eva2.eva2.service;

import eva2.eva2.model.Invocacion;
import eva2.eva2.repository.InvocacionRepository;
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
class InvocacionServiceTest {

    @Mock
    private InvocacionRepository invocacionRepository;

    @InjectMocks
    private InvocacionService invocacionService;

    @Test
    void testFindAll() {
        when(invocacionRepository.findAll()).thenReturn(List.of(new Invocacion()));
        assertEquals(1, invocacionService.findAll().size());
    }

    @Test
    void testFindById() {
        when(invocacionRepository.findById(1))
                .thenReturn(Optional.of(new Invocacion()));

        assertNotNull(invocacionService.findById(1));
    }

    @Test
    void testSave() {
        Invocacion invocacion = new Invocacion();

        when(invocacionRepository.save(invocacion))
                .thenReturn(invocacion);

        assertNotNull(invocacionService.save(invocacion));
    }

    @Test
    void testDelete() {
        invocacionService.delete(1);
        verify(invocacionRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {

        when(invocacionRepository.findByNombre("Dragón"))
                .thenReturn(new Invocacion());

        assertNotNull(
                invocacionService.findByNombre("Dragón")
        );
    }

    @Test
    void testFindByNivel() {

        when(invocacionRepository.findByNivel(8))
                .thenReturn(new Invocacion());

        assertNotNull(
                invocacionService.findByNivel(8)
        );
    }
}