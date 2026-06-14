package eva2.eva2.controler;

import eva2.eva2.model.Encantamiento;
import eva2.eva2.service.EncantamientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Encantamientos")
@Tag(name = "encantamientos", description = "gestiona los encantamientos del sistema")
public class EncantamientoController {

    @Autowired
    private EncantamientoService encantamientoService;

    @PostMapping
    @Operation(summary = "crear un encantamiento", description = "registra un nuevo encantamiento en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encantamiento creado exitosamente")
    })
    public ResponseEntity<Encantamiento> crear(@RequestBody Encantamiento encantamiento){
        return ResponseEntity.ok(
                encantamientoService.save(encantamiento)
        );
    }

    @GetMapping
    @Operation(summary = "listar encantamientos", description = "obtiene todos los encantamientos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de encantamientos obtenida")
    })
    public ResponseEntity<List<Encantamiento>> mostrar(){
        return ResponseEntity.ok(
                encantamientoService.findAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar encantamiento por ID", description = "obtiene un encantamiento por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encantamiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Encantamiento no encontrado")
    })
    public ResponseEntity<Encantamiento> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                encantamientoService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar encantamiento por nombre", description = "obtiene un encantamiento por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encantamiento encontrado"),
            @ApiResponse(responseCode = "404", description = "Encantamiento no encontrado")
    })
    public ResponseEntity<Encantamiento> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                encantamientoService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar encantamiento", description = "actualiza los datos de un encantamiento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encantamiento actualizado"),
            @ApiResponse(responseCode = "404", description = "Encantamiento no encontrado")
    })
    public ResponseEntity<Encantamiento> actualizar(
            @PathVariable Integer id,
            @RequestBody Encantamiento encantamiento){

        Encantamiento e = encantamientoService.findById(id);

        e.setNombre(encantamiento.getNombre());
        e.setNivel(encantamiento.getNivel());

        return ResponseEntity.ok(
                encantamientoService.save(e)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar encantamiento", description = "elimina un encantamiento por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Encantamiento eliminado"),
            @ApiResponse(responseCode = "404", description = "Encantamiento no encontrado")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        encantamientoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}