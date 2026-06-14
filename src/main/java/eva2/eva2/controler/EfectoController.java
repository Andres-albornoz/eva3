package eva2.eva2.controler;

import eva2.eva2.model.Efecto;
import eva2.eva2.service.EfectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Efectos")
public class EfectoController {

    @Autowired
    private EfectoService efectoService;

    @PostMapping
    public ResponseEntity<Efecto> crear(@RequestBody Efecto efecto){
        return ResponseEntity.ok(efectoService.save(efecto));
    }

    @GetMapping
    public ResponseEntity<List<Efecto>> mostrar(){
        return ResponseEntity.ok(efectoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Efecto> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(efectoService.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Efecto> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(efectoService.findByNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Efecto> actualizar(
            @PathVariable Integer id,
            @RequestBody Efecto efecto){

        Efecto e = efectoService.findById(id);

        e.setNombre(efecto.getNombre());
        e.setPotencia(efecto.getPotencia());

        return ResponseEntity.ok(efectoService.save(e));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        efectoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}