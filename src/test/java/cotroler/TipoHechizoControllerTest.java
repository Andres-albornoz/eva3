package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.TipoHechizoController;
import eva2.eva2.model.TipoHechizo;
import eva2.eva2.service.TipoHechizoService;
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

@WebMvcTest(TipoHechizoController.class)
public class TipoHechizoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TipoHechizoService tipoHechizoService;

    @Autowired
    private ObjectMapper objectMapper;

    private TipoHechizo tipoHechizo;

    @BeforeEach
    void setUp() {
        tipoHechizo = new TipoHechizo();
        tipoHechizo.setId(1);
        tipoHechizo.setNombre("Ataque");
    }

    @Test
    void testCrear() throws Exception {
        when(tipoHechizoService.save(any(TipoHechizo.class))).thenReturn(tipoHechizo);

        mockMvc.perform(post("/api/v1/TiposHechizo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoHechizo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Ataque"));
    }

    @Test
    void testMostrar() throws Exception {
        when(tipoHechizoService.findAll()).thenReturn(List.of(tipoHechizo));

        mockMvc.perform(get("/api/v1/TiposHechizo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ataque"));
    }

    @Test
    void testBuscar() throws Exception {
        when(tipoHechizoService.findById(1)).thenReturn(tipoHechizo);

        mockMvc.perform(get("/api/v1/TiposHechizo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ataque"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(tipoHechizoService.findByNombre("Ataque")).thenReturn(tipoHechizo);

        mockMvc.perform(get("/api/v1/TiposHechizo/nombre/Ataque"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {
        when(tipoHechizoService.findById(1)).thenReturn(tipoHechizo);
        when(tipoHechizoService.save(any(TipoHechizo.class))).thenReturn(tipoHechizo);

        mockMvc.perform(put("/api/v1/TiposHechizo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tipoHechizo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ataque"));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(tipoHechizoService).delete(1);

        mockMvc.perform(delete("/api/v1/TiposHechizo/1"))
                .andExpect(status().isNoContent());

        verify(tipoHechizoService, times(1)).delete(1);
    }
}
