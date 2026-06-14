package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.EscuelaMagicaController;
import eva2.eva2.model.EscuelaMagica;
import eva2.eva2.service.EscuelaMagicaService;
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

@WebMvcTest(EscuelaMagicaController.class)
public class EscuelaMagicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EscuelaMagicaService escuelaMagicaService;

    @Autowired
    private ObjectMapper objectMapper;

    private EscuelaMagica escuela;

    @BeforeEach
    void setUp() {
        escuela = new EscuelaMagica();
        escuela.setId(1);
        escuela.setNombre("Academia Arcana");
        escuela.setEspecialidad("Ilusion");
    }

    @Test
    void testCrear() throws Exception {
        when(escuelaMagicaService.save(any(EscuelaMagica.class))).thenReturn(escuela);

        mockMvc.perform(post("/api/v1/Escuelas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(escuela)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Academia Arcana"));
    }

    @Test
    void testMostrar() throws Exception {
        when(escuelaMagicaService.findAll()).thenReturn(List.of(escuela));

        mockMvc.perform(get("/api/v1/Escuelas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].especialidad").value("Ilusion"));
    }

    @Test
    void testBuscar() throws Exception {
        when(escuelaMagicaService.findById(1)).thenReturn(escuela);

        mockMvc.perform(get("/api/v1/Escuelas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Academia Arcana"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(escuelaMagicaService.findByNombre("Academia Arcana")).thenReturn(escuela);

        mockMvc.perform(get("/api/v1/Escuelas/nombre/Academia Arcana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {
        when(escuelaMagicaService.findById(1)).thenReturn(escuela);
        when(escuelaMagicaService.save(any(EscuelaMagica.class))).thenReturn(escuela);

        mockMvc.perform(put("/api/v1/Escuelas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(escuela)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.especialidad").value("Ilusion"));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(escuelaMagicaService).delete(1);

        mockMvc.perform(delete("/api/v1/Escuelas/1"))
                .andExpect(status().isNoContent());

        verify(escuelaMagicaService, times(1)).delete(1);
    }
}
