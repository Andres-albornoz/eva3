package eva2.eva2.controler;

import eva2.eva2.model.Catalizador;
import eva2.eva2.service.CatalizadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Catalizadores")
@Tag(name = "catalizadores", description = "gestiona los catalizadores del sistema")
public class CatalizadorController {

    @Autowired
    private CatalizadorService catalizadorService;

    @PostMapping
    @Operation(summary = "crear un catalizador", description = "registra un nuevo catalizador en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catalizador creado exitosamente")
    })
    public ResponseEntity<Catalizador> crear(@RequestBody Catalizador catalizador){
        return ResponseEntity.ok(
                catalizadorService.save(catalizador)
        );
    }

    @GetMapping
    @Operation(summary = "listar catalizadores", description = "obtiene todos los catalizadores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de catalizadores obtenida")
    })
    public ResponseEntity<List<Catalizador>> mostrar(){
        return ResponseEntity.ok(
                catalizadorService.findAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar catalizador por ID", description = "obtiene un catalizador por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catalizador encontrado"),
            @ApiResponse(responseCode = "404", description = "Catalizador no encontrado")
    })
    public ResponseEntity<Catalizador> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                catalizadorService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar catalizador por nombre", description = "obtiene un catalizador por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catalizador encontrado"),
            @ApiResponse(responseCode = "404", description = "Catalizador no encontrado")
    })
    public ResponseEntity<Catalizador> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                catalizadorService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar catalizador", description = "actualiza los datos de un catalizador existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catalizador actualizado"),
            @ApiResponse(responseCode = "404", description = "Catalizador no encontrado")
    })
    public ResponseEntity<Catalizador> actualizar(
            @PathVariable Integer id,
            @RequestBody Catalizador catalizador){

        Catalizador c = catalizadorService.findById(id);

        c.setNombre(catalizador.getNombre());
        c.setConsumible(catalizador.isConsumible());

        return ResponseEntity.ok(
                catalizadorService.save(c)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar catalizador", description = "elimina un catalizador por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Catalizador eliminado"),
            @ApiResponse(responseCode = "404", description = "Catalizador no encontrado")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        catalizadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}