package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eva2.eva2.controler.EfectoController;
import eva2.eva2.model.Efecto;
import eva2.eva2.service.EfectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EfectoController.class)
public class EfectoControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EfectoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Efecto objeto;

    @BeforeEach
    void setUp() {
        objeto = new Efecto();

        // Ajustar según la entidad
        objeto.setId(1);
    }

    @Test
    void testCrear() throws Exception {

        when(service.save(any(Efecto.class)))
                .thenReturn(objeto);

        mockMvc.perform(post("RUTA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(objeto)))
                .andExpect(status().isOk());
    }

    @Test
    void testMostrar() throws Exception {

        when(service.findAll())
                .thenReturn(List.of(objeto));

        mockMvc.perform(get("RUTA"))
                .andExpect(status().isOk());
    }

    @Test
    void testBuscar() throws Exception {

        when(service.findById(1))
                .thenReturn(objeto);

        mockMvc.perform(get("RUTA/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testActualizar() throws Exception {

        when(service.findById(1))
                .thenReturn(objeto);

        when(service.save(any(Efecto.class)))
                .thenReturn(objeto);

        mockMvc.perform(put("RUTA/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(objeto)))
                .andExpect(status().isOk());
    }

    @Test
    void testBorrar() throws Exception {

        doNothing().when(service).delete(1);

        mockMvc.perform(delete("RUTA/1"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).delete(1);
    }
}