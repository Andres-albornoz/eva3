package eva2.eva2.controler;

import eva2.eva2.model.Hechizo;
import eva2.eva2.model.HechizoDTO;
import eva2.eva2.service.HechizoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/Hechizos")
@Tag(name = "hehizos", description = "gestiona la creaacion, busqueda y organizacion de los hechizos ")
public class HechizoControler {

    @Autowired
    private HechizoService HechizoService;

    //crear Hechizos
    @PostMapping
    @Operation(summary = "creacion de hechizos", description = "permte la creacion de hechisos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    public ResponseEntity<Hechizo> crear(@RequestBody HechizoDTO dto) {
        try {
            Hechizo H = new Hechizo();
            H.setNombre(dto.getNombre());
            H.setAtributo(dto.getAtributo());
            H.setNivel(0);
            H.setEnPos(false);

            HechizoService.save(H);
            log.info("Hechizo creado correctamente: {}", H.getNombre());
            return ResponseEntity.ok(H);
        } catch (Exception e) {
            log.error("Error al crear hechizo: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    //mostrar Hechizos
    @GetMapping
    @Operation(summary = "obtener los hechisos", description = "obtiene y muesta la lista coplet de hechizos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa")
    })
    public ResponseEntity<List<Hechizo>> mostrar() {
        List<Hechizo> Hechizo  = HechizoService.findAll();
        if (Hechizo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Hechizo);
    }

    //buscar Hechizo Por
    @GetMapping("/{id}")
    @Operation(summary = "busca por el id", description = "muestra unicamente el hechizo pedido por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "hechizo no encontrado")
    })
    public ResponseEntity<Hechizo> buscar(@PathVariable Integer id) {
        try {
            Hechizo Hechizo = HechizoService.findById(id);
            return ResponseEntity.ok(Hechizo);
        } catch ( Exception e ) {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/atributo/{atributo}")
    @Operation(summary = "buscar por el atributo", description = "muestra unicamente los hechizos con el atributo dado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "hechizos no encontrados")
    })
    public ResponseEntity<Hechizo> buscarPAtr(@PathVariable String atributo) {
        try {
            Hechizo hechizo = HechizoService.findByAtributo(atributo);
            return ResponseEntity.ok(hechizo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nivel/{nivel}")
    @Operation(summary = "buscar por el nivel", description = "muestra unicamente los hechizos con el nivel dado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "hechizos no encontrados")
    })
    public ResponseEntity<Hechizo> buscarPNvl(@PathVariable int nivel) {
        try {
            Hechizo hechizo = HechizoService.findByNivel(nivel);
            return ResponseEntity.ok(hechizo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/enpos/{enPos}")
    @Operation(summary = "buscar por 'enPos'", description = "muestra unicamente los hechizos con el atributo 'enPos'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "hechizos no encontrados")
    })
    public ResponseEntity<Hechizo> buscarEnPos(@PathVariable boolean enPos) {
        try {
            Hechizo hechizo = HechizoService.findByEnPos(enPos);
            return ResponseEntity.ok(hechizo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //actualizar Hechizos
    @PutMapping("/{id}")
    @Operation(summary = "actualizar/modificar un hehizo", description = "actualiza o modifica lo atrubutos base de un hechizo")@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "hechizo no encontrado")
    })
    public ResponseEntity<Hechizo> actualizar(@PathVariable Integer id,@RequestBody HechizoDTO dto) {
        try {
            Hechizo H = HechizoService.findById(id);

            H.setNombre(dto.getNombre());
            H.setAtributo(dto.getAtributo());

            HechizoService.save(H);
            log.info("Hechizo actualizado correctamente: {}", H.getNombre());
            return ResponseEntity.ok(H);
        } catch ( Exception e ) {
            log.error("Error al actualizr hechizo: {}", e.getMessage());
            return  ResponseEntity.notFound().build();
        }
    }

    //actualizar solo un atributo
    @PutMapping("/{id}/enPos")
    @Operation(summary = "actualizar el 'enPos'", description = "actualiza el 'enPos' de un hechizo")@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "hechizo no encontrado")
    })
    public ResponseEntity<Hechizo> actualizarEnPos(@PathVariable Integer id) {
        try {
            Hechizo H = HechizoService.findById(id);
            H.setEnPos(true);
            HechizoService.save(H);
            log.info("Hechizo actualizado correctamente: {}", H.getNombre());
            return ResponseEntity.ok(H);
        } catch (Exception e) {
            log.error("Error al actualizr hechizo: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/nivel")
    @Operation(summary = "actualizar el nive", description = "actualiza el nive de un hechizo")@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "hechizo no encontrado")
    })
    public ResponseEntity<Hechizo> actualizarnivel(@PathVariable Integer id) {
        try {
            Hechizo H = HechizoService.findById(id);
            int random = (int) (Math.random() * 10);
            H.setNivel(random);
            HechizoService.save(H);
            log.info("Hechizo actualizado correctamente: {}", H.getNombre());
            return ResponseEntity.ok(H);
        } catch (Exception e) {
            log.error("Error al actualizr hechizo: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    //borrar
    @DeleteMapping("/{id}")
    @Operation(summary = "borra un hechizo", description = "borra un hechizo concreato por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "hechizo no encontrado")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id) {
        try {
            HechizoService.delete(id);
            log.info("Hechizo borrado correctamente");
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            log.error("Error al borrar hechizo: Hechizo not found: {}", e.getMessage());
            return  ResponseEntity.notFound().build();
        }
    }
}
