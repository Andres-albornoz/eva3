package cotroler;

import eva2.eva2.model.Hechizo;
import eva2.eva2.model.HechizoDTO;
import eva2.eva2.service.HechizoService;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HechizoControler.class)
public class HechizoControler {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HechizoService HechizoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Hechizo Hechizo;

    @BeforeEach
    void setUp() {
        Hechizo = new Hechizo();
        Hechizo.setId(1);
        Hechizo.setNombre("Hechizo1");
        Hechizo.setAtributo("atributo1");
        Hechizo.setNivel(1);
        Hechizo.setEnPos(true);
    }

    @Test
    public void testCrear() throws Exception {
        when(HechizoService.save(any(Hechizo.class))).thenReturn(Hechizo);

        mockMvc.perform(post("/api/Hechizo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Hechizo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Hechizo1"));
    }

    @Test
    public void testGetAll() throws Exception {
        when(HechizoService.findAll()).thenReturn(List.of(Hechizo));

        mockMvc.perform(get("/api/salas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Hechizo1"));
    }

    @Test
    public void testGeById() throws Exception {
        when(HechizoService.findById(1)).thenReturn(Hechizo);

        mockMvc.perform(get("/api/Hechizo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Hechizo1"));
    }

    @Test
    public void testBuscarPorAtributo() throws Exception {
        when(HechizoService.findByAtributo("Fuego")).thenReturn(Hechizo);

        mockMvc.perform(get("/api/Hechizo/atributo/Fuego"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Hechizo1"));
    }

    @Test
    public void testBuscarPorNivel() throws Exception {
        when(HechizoService.findByNivel(5)).thenReturn(Hechizo);

        mockMvc.perform(get("/api/Hechizo/nivel/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Hechizo1"));
    }

    @Test
    public void testBuscarEnPos() throws Exception {
        when(HechizoService.findByEnPos(true)).thenReturn(Hechizo);

        mockMvc.perform(get("/api/Hechizo/enPos/true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Hechizo1"));
    }


    @Test
    public void testUpdate() throws Exception {
        when(HechizoService.save(any(Hechizo.class))).thenReturn(Hechizo);

        mockMvc.perform(put("/api/Hechizo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Hechizo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Hechizo1"));
    }


    @Test
    public void testActualizarEnPos() throws Exception {
        Hechizo.setEnPos(false);

        when(HechizoService.findById(1)).thenReturn(Hechizo);
        when(HechizoService.save(any(Hechizo.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(put("/api/Hechizo/enPos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enPos").value(true));
    }

    @Test
    public void testActualizarNivel() throws Exception {
        when(HechizoService.findById(1)).thenReturn(Hechizo);
        when(HechizoService.save(any(Hechizo.class))).thenAnswer(i -> i.getArgument(0));

        mockMvc.perform(put("/api/Hechizo/nivelRandom/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Hechizo1"));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(HechizoService).delete(1);

        mockMvc.perform(delete("/api/Hechizo/1"))
                .andExpect(status().isOk());

        verify(HechizoService, times(1)).delete(1);
    }
}
