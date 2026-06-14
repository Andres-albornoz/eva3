package eva2.eva2.controler;

import eva2.eva2.model.Grimorio;
import eva2.eva2.service.GrimorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Grimorios")
public class GrimorioController {

    @Autowired
    private GrimorioService grimorioService;

    @PostMapping
    public ResponseEntity<Grimorio> crear(@RequestBody Grimorio grimorio){
        return ResponseEntity.ok(grimorioService.save(grimorio));
    }

    @GetMapping
    public ResponseEntity<List<Grimorio>> mostrar(){
        return ResponseEntity.ok(grimorioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grimorio> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(grimorioService.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Grimorio> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(grimorioService.findByNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grimorio> actualizar(
            @PathVariable Integer id,
            @RequestBody Grimorio grimorio){

        Grimorio g = grimorioService.findById(id);

        g.setNombre(grimorio.getNombre());
        g.setCapacidad(grimorio.getCapacidad());

        return ResponseEntity.ok(grimorioService.save(g));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        grimorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}