package eva2.eva2.controler;

import eva2.eva2.model.EscuelaMagica;
import eva2.eva2.service.EscuelaMagicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Escuelas")
public class EscuelaMagicaController {

    @Autowired
    private EscuelaMagicaService escuelaMagicaService;

    @PostMapping
    public ResponseEntity<EscuelaMagica> crear(@RequestBody EscuelaMagica escuela){
        return ResponseEntity.ok(
                escuelaMagicaService.save(escuela)
        );
    }

    @GetMapping
    public ResponseEntity<List<EscuelaMagica>> mostrar(){
        return ResponseEntity.ok(
                escuelaMagicaService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscuelaMagica> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                escuelaMagicaService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<EscuelaMagica> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                escuelaMagicaService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        escuelaMagicaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}