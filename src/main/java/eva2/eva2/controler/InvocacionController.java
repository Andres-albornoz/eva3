package eva2.eva2.controler;

import eva2.eva2.model.Invocacion;
import eva2.eva2.service.InvocacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Invocaciones")
@Tag(name = "invocaciones", description = "gestiona las invocaciones del sistema")
public class InvocacionController {

    @Autowired
    private InvocacionService invocacionService;

    @PostMapping
    @Operation(summary = "crear una invocación", description = "registra una nueva invocación en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invocación creada exitosamente")
    })
    public ResponseEntity<Invocacion> crear(@RequestBody Invocacion invocacion){
        return ResponseEntity.ok(
                invocacionService.save(invocacion)
        );
    }

    @GetMapping
    @Operation(summary = "listar invocaciones", description = "obtiene todas las invocaciones registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de invocaciones obtenida")
    })
    public ResponseEntity<List<Invocacion>> mostrar(){
        return ResponseEntity.ok(
                invocacionService.findAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar invocación por ID", description = "obtiene una invocación por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invocación encontrada"),
            @ApiResponse(responseCode = "404", description = "Invocación no encontrada")
    })
    public ResponseEntity<Invocacion> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                invocacionService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar invocación por nombre", description = "obtiene una invocación por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invocación encontrada"),
            @ApiResponse(responseCode = "404", description = "Invocación no encontrada")
    })
    public ResponseEntity<Invocacion> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                invocacionService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar invocación", description = "actualiza los datos de una invocación existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invocación actualizada"),
            @ApiResponse(responseCode = "404", description = "Invocación no encontrada")
    })
    public ResponseEntity<Invocacion> actualizar(
            @PathVariable Integer id,
            @RequestBody Invocacion invocacion){

        Invocacion i = invocacionService.findById(id);

        i.setNombre(invocacion.getNombre());
        i.setNivel(invocacion.getNivel());

        return ResponseEntity.ok(
                invocacionService.save(i)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar invocación", description = "elimina una invocación por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Invocación eliminada"),
            @ApiResponse(responseCode = "404", description = "Invocación no encontrada")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        invocacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}