package eva2.eva2.controler;

import eva2.eva2.model.Grimorio;
import eva2.eva2.service.GrimorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Grimorios")
@Tag(name = "grimorios", description = "gestiona los grimorios del sistema")
public class GrimorioController {

    @Autowired
    private GrimorioService grimorioService;

    @PostMapping
    @Operation(summary = "crear un grimorio", description = "registra un nuevo grimorio en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grimorio creado exitosamente")
    })
    public ResponseEntity<Grimorio> crear(@RequestBody Grimorio grimorio){
        return ResponseEntity.ok(grimorioService.save(grimorio));
    }

    @GetMapping
    @Operation(summary = "listar grimorios", description = "obtiene todos los grimorios registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de grimorios obtenida")
    })
    public ResponseEntity<List<Grimorio>> mostrar(){
        return ResponseEntity.ok(grimorioService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar grimorio por ID", description = "obtiene un grimorio por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grimorio encontrado"),
            @ApiResponse(responseCode = "404", description = "Grimorio no encontrado")
    })
    public ResponseEntity<Grimorio> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(grimorioService.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar grimorio por nombre", description = "obtiene un grimorio por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grimorio encontrado"),
            @ApiResponse(responseCode = "404", description = "Grimorio no encontrado")
    })
    public ResponseEntity<Grimorio> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(grimorioService.findByNombre(nombre));
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar grimorio", description = "actualiza los datos de un grimorio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grimorio actualizado"),
            @ApiResponse(responseCode = "404", description = "Grimorio no encontrado")
    })
    public ResponseEntity<Grimorio> actualizar(
            @PathVariable Integer id,
            @RequestBody Grimorio grimorio){

        Grimorio g = grimorioService.findById(id);

        g.setNombre(grimorio.getNombre());
        g.setCapacidad(grimorio.getCapacidad());

        return ResponseEntity.ok(grimorioService.save(g));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar grimorio", description = "elimina un grimorio por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Grimorio eliminado"),
            @ApiResponse(responseCode = "404", description = "Grimorio no encontrado")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        grimorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}