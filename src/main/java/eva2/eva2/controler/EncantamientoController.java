package eva2.eva2.controler;

import eva2.eva2.model.Encantamiento;
import eva2.eva2.service.EncantamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Encantamientos")
public class EncantamientoController {

    @Autowired
    private EncantamientoService encantamientoService;

    @PostMapping
    public ResponseEntity<Encantamiento> crear(@RequestBody Encantamiento encantamiento){
        return ResponseEntity.ok(
                encantamientoService.save(encantamiento)
        );
    }

    @GetMapping
    public ResponseEntity<List<Encantamiento>> mostrar(){
        return ResponseEntity.ok(
                encantamientoService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encantamiento> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                encantamientoService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Encantamiento> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                encantamientoService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        encantamientoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}