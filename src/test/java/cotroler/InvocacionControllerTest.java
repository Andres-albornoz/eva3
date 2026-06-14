package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.InvocacionController;
import eva2.eva2.model.Invocacion;
import eva2.eva2.service.InvocacionService;
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

@WebMvcTest(InvocacionController.class)
public class InvocacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InvocacionService invocacionService;

    @Autowired
    private ObjectMapper objectMapper;

    private Invocacion invocacion;

    @BeforeEach
    void setUp() {
        invocacion = new Invocacion();
        invocacion.setId(1);
        invocacion.setNombre("Fenix");
        invocacion.setNivel(5);
    }

    @Test
    void testCrear() throws Exception {
        when(invocacionService.save(any(Invocacion.class))).thenReturn(invocacion);

        mockMvc.perform(post("/api/v1/Invocaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invocacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Fenix"));
    }

    @Test
    void testMostrar() throws Exception {
        when(invocacionService.findAll()).thenReturn(List.of(invocacion));

        mockMvc.perform(get("/api/v1/Invocaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nivel").value(5));
    }

    @Test
    void testBuscar() throws Exception {
        when(invocacionService.findById(1)).thenReturn(invocacion);

        mockMvc.perform(get("/api/v1/Invocaciones/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Fenix"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(invocacionService.findByNombre("Fenix")).thenReturn(invocacion);

        mockMvc.perform(get("/api/v1/Invocaciones/nombre/Fenix"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {
        when(invocacionService.findById(1)).thenReturn(invocacion);
        when(invocacionService.save(any(Invocacion.class))).thenReturn(invocacion);

        mockMvc.perform(put("/api/v1/Invocaciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invocacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nivel").value(5));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(invocacionService).delete(1);

        mockMvc.perform(delete("/api/v1/Invocaciones/1"))
                .andExpect(status().isNoContent());

        verify(invocacionService, times(1)).delete(1);
    }
}
