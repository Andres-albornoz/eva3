package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.GrimorioController;
import eva2.eva2.model.Grimorio;
import eva2.eva2.service.GrimorioService;
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

@WebMvcTest(GrimorioController.class)
public class GrimorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GrimorioService grimorioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Grimorio grimorio;

    @BeforeEach
    void setUp() {
        grimorio = new Grimorio();
        grimorio.setId(1);
        grimorio.setNombre("Libro de sombras");
        grimorio.setCapacidad(25);
    }

    @Test
    void testCrear() throws Exception {
        when(grimorioService.save(any(Grimorio.class))).thenReturn(grimorio);

        mockMvc.perform(post("/api/v1/Grimorios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grimorio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Libro de sombras"));
    }

    @Test
    void testMostrar() throws Exception {
        when(grimorioService.findAll()).thenReturn(List.of(grimorio));

        mockMvc.perform(get("/api/v1/Grimorios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].capacidad").value(25));
    }

    @Test
    void testBuscar() throws Exception {
        when(grimorioService.findById(1)).thenReturn(grimorio);

        mockMvc.perform(get("/api/v1/Grimorios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Libro de sombras"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(grimorioService.findByNombre("Libro de sombras")).thenReturn(grimorio);

        mockMvc.perform(get("/api/v1/Grimorios/nombre/Libro de sombras"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {
        when(grimorioService.findById(1)).thenReturn(grimorio);
        when(grimorioService.save(any(Grimorio.class))).thenReturn(grimorio);

        mockMvc.perform(put("/api/v1/Grimorios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(grimorio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capacidad").value(25));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(grimorioService).delete(1);

        mockMvc.perform(delete("/api/v1/Grimorios/1"))
                .andExpect(status().isNoContent());

        verify(grimorioService, times(1)).delete(1);
    }
}
