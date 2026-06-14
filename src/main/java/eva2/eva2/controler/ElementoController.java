package eva2.eva2.controler;

import eva2.eva2.model.Elemento;
import eva2.eva2.service.ElementoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Elementos")
public class ElementoController {

    @Autowired
    private ElementoService elementoService;

    @PostMapping
    public ResponseEntity<Elemento> crear(@RequestBody Elemento elemento){
        return ResponseEntity.ok(
                elementoService.save(elemento)
        );
    }

    @GetMapping
    public ResponseEntity<List<Elemento>> mostrar(){
        return ResponseEntity.ok(
                elementoService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Elemento> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                elementoService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Elemento> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                elementoService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        elementoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}