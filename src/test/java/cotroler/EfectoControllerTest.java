package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.EfectoController;
import eva2.eva2.model.Efecto;
import eva2.eva2.service.EfectoService;
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

@WebMvcTest(EfectoController.class)
public class EfectoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EfectoService efectoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Efecto efecto;

    @BeforeEach
    void setUp() {
        efecto = new Efecto();
        efecto.setId(1);
        efecto.setNombre("Quemadura");
        efecto.setPotencia(7);
    }

    @Test
    void testCrear() throws Exception {
        when(efectoService.save(any(Efecto.class))).thenReturn(efecto);

        mockMvc.perform(post("/api/v1/Efectos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(efecto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Quemadura"));
    }

    @Test
    void testMostrar() throws Exception {
        when(efectoService.findAll()).thenReturn(List.of(efecto));

        mockMvc.perform(get("/api/v1/Efectos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].potencia").value(7));
    }

    @Test
    void testBuscar() throws Exception {
        when(efectoService.findById(1)).thenReturn(efecto);

        mockMvc.perform(get("/api/v1/Efectos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Quemadura"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(efectoService.findByNombre("Quemadura")).thenReturn(efecto);

        mockMvc.perform(get("/api/v1/Efectos/nombre/Quemadura"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {
        when(efectoService.findById(1)).thenReturn(efecto);
        when(efectoService.save(any(Efecto.class))).thenReturn(efecto);

        mockMvc.perform(put("/api/v1/Efectos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(efecto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.potencia").value(7));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(efectoService).delete(1);

        mockMvc.perform(delete("/api/v1/Efectos/1"))
                .andExpect(status().isNoContent());

        verify(efectoService, times(1)).delete(1);
    }
}
