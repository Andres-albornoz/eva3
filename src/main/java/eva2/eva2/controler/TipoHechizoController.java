package eva2.eva2.controler;

import eva2.eva2.model.TipoHechizo;
import eva2.eva2.service.TipoHechizoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/TiposHechizo")
@Tag(name = "tipos de hechizo", description = "gestiona los tipos de hechizo del sistema")
public class TipoHechizoController {

    @Autowired
    private TipoHechizoService tipoHechizoService;

    @PostMapping
    @Operation(summary = "crear un tipo de hechizo", description = "registra un nuevo tipo de hechizo en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de hechizo creado exitosamente")
    })
    public ResponseEntity<TipoHechizo> crear(@RequestBody TipoHechizo tipo){
        return ResponseEntity.ok(tipoHechizoService.save(tipo));
    }

    @GetMapping
    @Operation(summary = "listar tipos de hechizo", description = "obtiene todos los tipos de hechizo registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tipos de hechizo obtenida")
    })
    public ResponseEntity<List<TipoHechizo>> mostrar(){
        return ResponseEntity.ok(tipoHechizoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar tipo de hechizo por ID", description = "obtiene un tipo de hechizo por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de hechizo encontrado"),
            @ApiResponse(responseCode = "404", description = "Tipo de hechizo no encontrado")
    })
    public ResponseEntity<TipoHechizo> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(tipoHechizoService.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar tipo de hechizo por nombre", description = "obtiene un tipo de hechizo por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de hechizo encontrado"),
            @ApiResponse(responseCode = "404", description = "Tipo de hechizo no encontrado")
    })
    public ResponseEntity<TipoHechizo> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(tipoHechizoService.findByNombre(nombre));
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar tipo de hechizo", description = "actualiza los datos de un tipo de hechizo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de hechizo actualizado"),
            @ApiResponse(responseCode = "404", description = "Tipo de hechizo no encontrado")
    })
    public ResponseEntity<TipoHechizo> actualizar(
            @PathVariable Integer id,
            @RequestBody TipoHechizo tipo){

        TipoHechizo t = tipoHechizoService.findById(id);

        t.setNombre(tipo.getNombre());

        return ResponseEntity.ok(tipoHechizoService.save(t));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar tipo de hechizo", description = "elimina un tipo de hechizo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de hechizo eliminado"),
            @ApiResponse(responseCode = "404", description = "Tipo de hechizo no encontrado")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        tipoHechizoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}