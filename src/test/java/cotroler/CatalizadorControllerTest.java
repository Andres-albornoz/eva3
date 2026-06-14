package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.CatalizadorController;
import eva2.eva2.model.Catalizador;
import eva2.eva2.service.CatalizadorService;
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

@WebMvcTest(CatalizadorController.class)
public class CatalizadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CatalizadorService catalizadorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Catalizador catalizador;

    @BeforeEach
    void setUp() {
        catalizador = new Catalizador();
        catalizador.setId(1);
        catalizador.setNombre("Cristal lunar");
        catalizador.setConsumible(true);
    }

    @Test
    void testCrear() throws Exception {
        when(catalizadorService.save(any(Catalizador.class))).thenReturn(catalizador);

        mockMvc.perform(post("/api/v1/Catalizadores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(catalizador)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Cristal lunar"));
    }

    @Test
    void testMostrar() throws Exception {
        when(catalizadorService.findAll()).thenReturn(List.of(catalizador));

        mockMvc.perform(get("/api/v1/Catalizadores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Cristal lunar"));
    }

    @Test
    void testBuscar() throws Exception {
        when(catalizadorService.findById(1)).thenReturn(catalizador);

        mockMvc.perform(get("/api/v1/Catalizadores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cristal lunar"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(catalizadorService.findByNombre("Cristal lunar")).thenReturn(catalizador);

        mockMvc.perform(get("/api/v1/Catalizadores/nombre/Cristal lunar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {
        when(catalizadorService.findById(1)).thenReturn(catalizador);
        when(catalizadorService.save(any(Catalizador.class))).thenReturn(catalizador);

        mockMvc.perform(put("/api/v1/Catalizadores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(catalizador)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cristal lunar"));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(catalizadorService).delete(1);

        mockMvc.perform(delete("/api/v1/Catalizadores/1"))
                .andExpect(status().isNoContent());

        verify(catalizadorService, times(1)).delete(1);
    }
}
