package eva2.eva2.controler;

import eva2.eva2.model.Elemento;
import eva2.eva2.service.ElementoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Elementos")
@Tag(name = "elementos", description = "gestiona los elementos del sistema")
public class ElementoController {

    @Autowired
    private ElementoService elementoService;

    @PostMapping
    @Operation(summary = "crear un elemento", description = "registra un nuevo elemento en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento creado exitosamente")
    })
    public ResponseEntity<Elemento> crear(@RequestBody Elemento elemento){
        return ResponseEntity.ok(
                elementoService.save(elemento)
        );
    }

    @GetMapping
    @Operation(summary = "listar elementos", description = "obtiene todos los elementos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de elementos obtenida")
    })
    public ResponseEntity<List<Elemento>> mostrar(){
        return ResponseEntity.ok(
                elementoService.findAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar elemento por ID", description = "obtiene un elemento por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")
    })
    public ResponseEntity<Elemento> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                elementoService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar elemento por nombre", description = "obtiene un elemento por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento encontrado"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")
    })
    public ResponseEntity<Elemento> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                elementoService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar elemento", description = "actualiza los datos de un elemento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elemento actualizado"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")
    })
    public ResponseEntity<Elemento> actualizar(
            @PathVariable Integer id,
            @RequestBody Elemento elemento){

        Elemento e = elementoService.findById(id);

        e.setNombre(elemento.getNombre());
        e.setDescripcion(elemento.getDescripcion());

        return ResponseEntity.ok(
                elementoService.save(e)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar elemento", description = "elimina un elemento por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Elemento eliminado"),
            @ApiResponse(responseCode = "404", description = "Elemento no encontrado")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        elementoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}