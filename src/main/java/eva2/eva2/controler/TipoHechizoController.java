package eva2.eva2.controler;

import eva2.eva2.model.TipoHechizo;
import eva2.eva2.service.TipoHechizoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/TiposHechizo")
public class TipoHechizoController {

    @Autowired
    private TipoHechizoService tipoHechizoService;

    @PostMapping
    public ResponseEntity<TipoHechizo> crear(@RequestBody TipoHechizo tipo){
        return ResponseEntity.ok(tipoHechizoService.save(tipo));
    }

    @GetMapping
    public ResponseEntity<List<TipoHechizo>> mostrar(){
        return ResponseEntity.ok(tipoHechizoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHechizo> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(tipoHechizoService.findById(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TipoHechizo> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(tipoHechizoService.findByNombre(nombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoHechizo> actualizar(
            @PathVariable Integer id,
            @RequestBody TipoHechizo tipo){

        TipoHechizo t = tipoHechizoService.findById(id);

        t.setNombre(tipo.getNombre());

        return ResponseEntity.ok(tipoHechizoService.save(t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        tipoHechizoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}