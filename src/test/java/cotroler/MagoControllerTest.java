package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.MagoController;
import eva2.eva2.model.Mago;
import eva2.eva2.service.MagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MagoController.class)
public class MagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MagoService magoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Mago mago;

    @BeforeEach
    void setUp() {
        mago = new Mago();
        mago.setId(1);
        mago.setNombre("Merlin");
        mago.setNivel(10);
        mago.setMana(100);
    }

    @Test
    void testCrear() throws Exception {
        when(magoService.save(any(Mago.class))).thenReturn(mago);

        mockMvc.perform(post("/api/v1/Magos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testMostrar() throws Exception {
        when(magoService.findAll()).thenReturn(List.of(mago));

        mockMvc.perform(get("/api/v1/Magos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Merlin"));
    }

    @Test
    void testBuscar() throws Exception {
        when(magoService.findById(1)).thenReturn(mago);

        mockMvc.perform(get("/api/v1/Magos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Merlin"));
    }

    @Test
    void testBuscarNombre() throws Exception {
        when(magoService.findByNombre("Merlin")).thenReturn(mago);

        mockMvc.perform(get("/api/v1/Magos/nombre/Merlin"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testActualizar() throws Exception {

        when(magoService.findById(1)).thenReturn(mago);
        when(magoService.save(any(Mago.class))).thenReturn(mago);

        mockMvc.perform(put("/api/v1/Magos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mago)))
                .andExpect(status().isOk());
    }

    @Test
    void testBorrar() throws Exception {

        doNothing().when(magoService).delete(1);

        mockMvc.perform(delete("/api/v1/Magos/1"))
                .andExpect(status().isNoContent());

        verify(magoService, times(1)).delete(1);
    }
}
