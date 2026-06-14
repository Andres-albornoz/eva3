package eva2.eva2.service;

import eva2.eva2.model.Catalizador;
import eva2.eva2.repository.CatalizadorRepository;
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
class CatalizadorServiceTest {

    @Mock
    private CatalizadorRepository catalizadorRepository;

    @InjectMocks
    private CatalizadorService catalizadorService;

    @Test
    void testFindAll() {
        when(catalizadorRepository.findAll()).thenReturn(List.of(new Catalizador()));
        assertEquals(1, catalizadorService.findAll().size());
    }

    @Test
    void testFindById() {
        when(catalizadorRepository.findById(1))
                .thenReturn(Optional.of(new Catalizador()));

        assertNotNull(catalizadorService.findById(1));
    }

    @Test
    void testSave() {
        Catalizador catalizador = new Catalizador();

        when(catalizadorRepository.save(catalizador))
                .thenReturn(catalizador);

        assertNotNull(catalizadorService.save(catalizador));
    }

    @Test
    void testDelete() {
        catalizadorService.delete(1);
        verify(catalizadorRepository).deleteById(1);
    }

    @Test
    void testFindByNombre() {

        when(catalizadorRepository.findByNombre("Varita"))
                .thenReturn(new Catalizador());

        assertNotNull(
                catalizadorService.findByNombre("Varita")
        );
    }

    @Test
    void testFindByConsumible() {

        when(catalizadorRepository.findByConsumible(true))
                .thenReturn(new Catalizador());

        assertNotNull(
                catalizadorService.findByConsumible(true)
        );
    }
}