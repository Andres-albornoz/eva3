package eva2.eva2.controler;

import eva2.eva2.model.Mago;
import eva2.eva2.service.MagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Magos")
@Tag(name = "magos", description = "gestiona los magos del sistema")
public class MagoController {

    @Autowired
    private MagoService magoService;

    @PostMapping
    @Operation(summary = "crear un mago", description = "registra un nuevo mago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mago creado exitosamente")
    })
    public ResponseEntity<Mago> crear(@RequestBody Mago mago){
        return ResponseEntity.ok(
                magoService.save(mago)
        );
    }

    @GetMapping
    @Operation(summary = "listar magos", description = "obtiene todos los magos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de magos obtenida")
    })
    public ResponseEntity<List<Mago>> mostrar(){
        return ResponseEntity.ok(
                magoService.findAll()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "buscar mago por ID", description = "obtiene un mago por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mago encontrado"),
            @ApiResponse(responseCode = "404", description = "Mago no encontrado")
    })
    public ResponseEntity<Mago> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                magoService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "buscar mago por nombre", description = "obtiene un mago por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mago encontrado"),
            @ApiResponse(responseCode = "404", description = "Mago no encontrado")
    })
    public ResponseEntity<Mago> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                magoService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
    @Operation(summary = "actualizar mago", description = "actualiza los datos de un mago existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mago actualizado"),
            @ApiResponse(responseCode = "404", description = "Mago no encontrado")
    })
    public ResponseEntity<Mago> actualizar(
            @PathVariable Integer id,
            @RequestBody Mago mago){

        Mago m = magoService.findById(id);

        m.setNombre(mago.getNombre());
        m.setNivel(mago.getNivel());
        m.setMana(mago.getMana());

        return ResponseEntity.ok(
                magoService.save(m)
        );
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "borrar mago", description = "elimina un mago por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Mago eliminado"),
            @ApiResponse(responseCode = "404", description = "Mago no encontrado")
    })
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        magoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}