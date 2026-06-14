package eva2.eva2.controler;

import eva2.eva2.model.EscuelaMagica;
import eva2.eva2.service.EscuelaMagicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Escuelas")
@Tag(name = "escuelas mágicas", description = "gestiona las escuelas mágicas del sistema")
public class EscuelaMagicaController {

    @Autowired
    private EscuelaMagicaService escuelaMagicaService;

    @PostMapping
    @Operation(summary = "crear una escuela mágica", description = "registra una nueva escuela mágica en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escuela mágica creada exitosamente")
    })
    public ResponseEntity<EscuelaMagica> crear(@RequestBody EscuelaMagica escuela){
        return ResponseEntity.ok(
                escuelaMagicaService.save(escuela)
        );
    }

    @GetMapping
    @Operation(summary = "listar escuelas mágicas", description = "obtiene todas las escuelas mágicas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de escuelas mágicas obtenida")
    })
    public ResponseEntity<List<EscuelaMagica>> mostrar(){
        return ResponseEntity.ok(
                escuelaMagicaService.findAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar escuela mágica por ID", description = "obtiene una escuela mágica por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escuela mágica encontrada"),
            @ApiResponse(responseCode = "404", description = "Escuela mágica no encontrada")
    })
    public ResponseEntity<EscuelaMagica> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                escuelaMagicaService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar escuela mágica por nombre", description = "obtiene una escuela mágica por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escuela mágica encontrada"),
            @ApiResponse(responseCode = "404", description = "Escuela mágica no encontrada")
    })
    public ResponseEntity<EscuelaMagica> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                escuelaMagicaService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar escuela mágica", description = "actualiza los datos de una escuela mágica existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escuela mágica actualizada"),
            @ApiResponse(responseCode = "404", description = "Escuela mágica no encontrada")
    })
    public ResponseEntity<EscuelaMagica> actualizar(
            @PathVariable Integer id,
            @RequestBody EscuelaMagica escuela){

        EscuelaMagica e = escuelaMagicaService.findById(id);

        e.setNombre(escuela.getNombre());
        e.setEspecialidad(escuela.getEspecialidad());

        return ResponseEntity.ok(
                escuelaMagicaService.save(e)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar escuela mágica", description = "elimina una escuela mágica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Escuela mágica eliminada"),
            @ApiResponse(responseCode = "404", description = "Escuela mágica no encontrada")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        escuelaMagicaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}