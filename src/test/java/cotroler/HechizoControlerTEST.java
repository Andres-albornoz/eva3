package cotroler;

import com.fasterxml.jackson.databind.ObjectMapper;
import eva2.eva2.controler.HechizoControler;
import eva2.eva2.model.Hechizo;
import eva2.eva2.model.HechizoDTO;
import eva2.eva2.service.HechizoService;
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

@WebMvcTest(HechizoControler.class)
public class HechizoControlerTEST {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HechizoService hechizoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Hechizo hechizo;
    private HechizoDTO hechizoDTO;

    @BeforeEach
    void setUp() {
        hechizo = new Hechizo();
        hechizo.setId(1);
        hechizo.setNombre("Bola de fuego");
        hechizo.setAtributo("Fuego");
        hechizo.setNivel(1);
        hechizo.setEnPos(true);

        hechizoDTO = new HechizoDTO();
        hechizoDTO.setNombre("Bola de fuego");
        hechizoDTO.setAtributo("Fuego");
    }

    @Test
    void testCrear() throws Exception {
        when(hechizoService.save(any(Hechizo.class))).thenReturn(hechizo);

        mockMvc.perform(post("/api/v1/Hechizos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hechizoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Bola de fuego"))
                .andExpect(jsonPath("$.atributo").value("Fuego"))
                .andExpect(jsonPath("$.nivel").value(0))
                .andExpect(jsonPath("$.enPos").value(false));
    }

    @Test
    void testMostrar() throws Exception {
        when(hechizoService.findAll()).thenReturn(List.of(hechizo));

        mockMvc.perform(get("/api/v1/Hechizos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Bola de fuego"));
    }

    @Test
    void testMostrarVacio() throws Exception {
        when(hechizoService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/v1/Hechizos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testBuscar() throws Exception {
        when(hechizoService.findById(1)).thenReturn(hechizo);

        mockMvc.perform(get("/api/v1/Hechizos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Bola de fuego"));
    }

    @Test
    void testBuscarPorAtributo() throws Exception {
        when(hechizoService.findByAtributo("Fuego")).thenReturn(hechizo);

        mockMvc.perform(get("/api/v1/Hechizos/atributo/Fuego"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.atributo").value("Fuego"));
    }

    @Test
    void testBuscarPorNivel() throws Exception {
        when(hechizoService.findByNivel(1)).thenReturn(hechizo);

        mockMvc.perform(get("/api/v1/Hechizos/nivel/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nivel").value(1));
    }

    @Test
    void testBuscarEnPos() throws Exception {
        when(hechizoService.findByEnPos(true)).thenReturn(hechizo);

        mockMvc.perform(get("/api/v1/Hechizos/enpos/true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.enPos").value(true));
    }

    @Test
    void testActualizar() throws Exception {
        when(hechizoService.findById(1)).thenReturn(hechizo);
        when(hechizoService.save(any(Hechizo.class))).thenReturn(hechizo);

        mockMvc.perform(put("/api/v1/Hechizos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(hechizoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Bola de fuego"))
                .andExpect(jsonPath("$.atributo").value("Fuego"));
    }

    @Test
    void testActualizarEnPos() throws Exception {
        hechizo.setEnPos(false);
        when(hechizoService.findById(1)).thenReturn(hechizo);
        when(hechizoService.save(any(Hechizo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(put("/api/v1/Hechizos/1/enPos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enPos").value(true));
    }

    @Test
    void testActualizarNivel() throws Exception {
        when(hechizoService.findById(1)).thenReturn(hechizo);
        when(hechizoService.save(any(Hechizo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        mockMvc.perform(put("/api/v1/Hechizos/1/nivel"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Bola de fuego"));
    }

    @Test
    void testBorrar() throws Exception {
        doNothing().when(hechizoService).delete(1);

        mockMvc.perform(delete("/api/v1/Hechizos/1"))
                .andExpect(status().isNoContent());

        verify(hechizoService, times(1)).delete(1);
    }
}
