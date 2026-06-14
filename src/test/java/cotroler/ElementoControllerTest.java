package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.ElementoController;
import eva2.eva2.model.Elemento;
import eva2.eva2.service.ElementoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ElementoController.class)
public class ElementoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ElementoService elementoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Elemento elemento;

    @BeforeEach
    void setUp() {
        elemento = new Elemento();
        elemento.setId(1);
        elemento.setNombre("Fuego");
        elemento.setDescripcion("Elemento ardiente");
    }

    @Test
    void testCrear() throws Exception {
        when(elementoService.save(any(Elemento.class))).thenReturn(elemento);

        mockMvc.perform(post("/api/v1/Elementos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(elemento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Fuego"));
    }

    @Test
    void testMostrar() throws Exception {
        when(elementoService.findAll()).thenReturn(List.of(elemento));

        mockMvc.perform(get("/api/v1/Elementos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descripcion").value("Elemento ardiente"));
    }

    @Test
    void testBuscar() throws Exception {
        when(elementoService.findById(1)).thenReturn(elemento);

        mockMvc.perform(get("/api/v1/Elementos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Fuego"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(elementoService.findByNombre("Fuego")).thenReturn(elemento);

        mockMvc.perform(get("/api/v1/Elementos/nombre/Fuego"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {
        when(elementoService.findById(1)).thenReturn(elemento);
        when(elementoService.save(any(Elemento.class))).thenReturn(elemento);

        mockMvc.perform(put("/api/v1/Elementos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(elemento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Fuego"));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(elementoService).delete(1);

        mockMvc.perform(delete("/api/v1/Elementos/1"))
                .andExpect(status().isNoContent());

        verify(elementoService, times(1)).delete(1);
    }
}
