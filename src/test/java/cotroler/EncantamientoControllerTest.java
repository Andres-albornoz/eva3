package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.EncantamientoController;
import eva2.eva2.model.Encantamiento;
import eva2.eva2.service.EncantamientoService;
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

@WebMvcTest(EncantamientoController.class)
public class EncantamientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EncantamientoService encantamientoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Encantamiento encantamiento;

    @BeforeEach
    void setUp() {
        encantamiento = new Encantamiento();
        encantamiento.setId(1);
        encantamiento.setNombre("Proteccion");
        encantamiento.setNivel(3);
    }

    @Test
    void testCrear() throws Exception {
        when(encantamientoService.save(any(Encantamiento.class))).thenReturn(encantamiento);

        mockMvc.perform(post("/api/v1/Encantamientos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(encantamiento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Proteccion"));
    }

    @Test
    void testMostrar() throws Exception {
        when(encantamientoService.findAll()).thenReturn(List.of(encantamiento));

        mockMvc.perform(get("/api/v1/Encantamientos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nivel").value(3));
    }

    @Test
    void testBuscar() throws Exception {
        when(encantamientoService.findById(1)).thenReturn(encantamiento);

        mockMvc.perform(get("/api/v1/Encantamientos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Proteccion"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(encantamientoService.findByNombre("Proteccion")).thenReturn(encantamiento);

        mockMvc.perform(get("/api/v1/Encantamientos/nombre/Proteccion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {
        when(encantamientoService.findById(1)).thenReturn(encantamiento);
        when(encantamientoService.save(any(Encantamiento.class))).thenReturn(encantamiento);

        mockMvc.perform(put("/api/v1/Encantamientos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(encantamiento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nivel").value(3));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(encantamientoService).delete(1);

        mockMvc.perform(delete("/api/v1/Encantamientos/1"))
                .andExpect(status().isNoContent());

        verify(encantamientoService, times(1)).delete(1);
    }
}
