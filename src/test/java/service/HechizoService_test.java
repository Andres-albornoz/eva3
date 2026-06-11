package service;

import eva2.eva2.model.Hechizo;
import eva2.eva2.repository.HechizoRepository;
import eva2.eva2.service.HechizoService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class HechizoService_test {

    @Mock
    private HechizoRepository hechizoRepository; // El mock simulado

    @InjectMocks
    private HechizoService hechizoService;

    @Test
    public void testFindAll(){
        // Convención: variables en minúscula (hechizoRepository)
        when(HechizoRepository.findAll()).thenReturn(List.of(new Hechizo(1, "HEHCIZO1", "atributo1", 1, true)));

        List<Hechizo> resultado = HechizoService.findAll(); // Uso de la instancia inyectada
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("HEHCIZO1", resultado.get(0).getNombre());
    }

    @Test
    public void testFindById() {
        int id = 1;
        Hechizo hechizo = new Hechizo(1, "HEHCIZO1", "atributo1", 1, true);
        when(HechizoRepository.findById(1)).thenReturn(Optional.of(hechizo));

        Hechizo found = HechizoService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Hechizo hechizo = new Hechizo(1, "HEHCIZO1", "atributo1", 1, true);
        when(HechizoRepository.save(hechizo)).thenReturn(hechizo);

        Hechizo saved = HechizoService.save(hechizo);
        assertNotNull(saved);
        assertEquals("HEHCIZO1", saved.getNombre());
    }

    @Test
    public void testDelete() {
        Integer id = 1;
        doNothing().when(HechizoRepository).deleteById(id);

        HechizoService.delete(id);
        verify(HechizoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testFindByAtributo() {
        Hechizo hechizo = new Hechizo(1, "HEHCIZO1", "atributo1", 1, true);
        // Ajustado para que devuelva Optional si es que tu repositorio trabaja así
        when(HechizoRepository.findByAtributo("atributo1")).thenReturn(Optional.of(hechizo));

        Hechizo found = HechizoService.findByAtributo("atributo1");
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    public void testFindByNivel() {
        Hechizo hechizo = new Hechizo(1, "HEHCIZO1", "atributo1", 1, true);
        // Asumiendo que findByNivel devuelve Optional en el repositorio
        when(HechizoRepository.findByNivel(1)).thenReturn(Optional.of(hechizo));

        Hechizo found = HechizoService.findByNivel(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    public void testFindByEnPos() {
        Hechizo hechizo = new Hechizo(1, "HEHCIZO1", "atributo1", 1, true);
        when(HechizoRepository.findByEnPos(true)).thenReturn(Optional.of(hechizo));

        Hechizo found = HechizoService.findByEnPos(true);
        assertNotNull(found);
        assertEquals(1, found.getId());
    }
}
