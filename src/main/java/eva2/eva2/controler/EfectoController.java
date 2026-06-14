package eva2.eva2.controler;

import eva2.eva2.model.Efecto;
import eva2.eva2.service.EfectoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Efectos")
@Tag(name = "efectos", description = "gestiona los efectos del sistema")
public class EfectoController {

    @Autowired
    private EfectoService efectoService;

    @PostMapping
    @Operation(summary = "crear un efecto", description = "registra un nuevo efecto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Efecto creado exitosamente")
    })
    public ResponseEntity<Efecto> crear(@RequestBody Efecto efecto){
        return ResponseEntity.ok(efectoService.save(efecto));
    }

    @GetMapping
    @Operation(summary = "listar efectos", description = "obtiene todos los efectos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de efectos obtenida")
    })
    public ResponseEntity<List<Efecto>> mostrar(){
        return ResponseEntity.ok(efectoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar efecto por ID", description = "obtiene un efecto por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Efecto encontrado"),
            @ApiResponse(responseCode = "404", description = "Efecto no encontrado")
    })
    public ResponseEntity<Efecto> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(efectoService.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar efecto por nombre", description = "obtiene un efecto por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Efecto encontrado"),
            @ApiResponse(responseCode = "404", description = "Efecto no encontrado")
    })
    public ResponseEntity<Efecto> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(efectoService.findByNombre(nombre));
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar efecto", description = "actualiza los datos de un efecto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Efecto actualizado"),
            @ApiResponse(responseCode = "404", description = "Efecto no encontrado")
    })
    public ResponseEntity<Efecto> actualizar(
            @PathVariable Integer id,
            @RequestBody Efecto efecto){

        Efecto e = efectoService.findById(id);

        e.setNombre(efecto.getNombre());
        e.setPotencia(efecto.getPotencia());

        return ResponseEntity.ok(efectoService.save(e));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar efecto", description = "elimina un efecto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Efecto eliminado"),
            @ApiResponse(responseCode = "404", description = "Efecto no encontrado")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        efectoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}