package eva2.eva2.controler;

import eva2.eva2.model.Catalizador;
import eva2.eva2.service.CatalizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Catalizadores")
public class CatalizadorController {

    @Autowired
    private CatalizadorService catalizadorService;

    @PostMapping
    public ResponseEntity<Catalizador> crear(@RequestBody Catalizador catalizador){
        return ResponseEntity.ok(
                catalizadorService.save(catalizador)
        );
    }

    @GetMapping
    public ResponseEntity<List<Catalizador>> mostrar(){
        return ResponseEntity.ok(
                catalizadorService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Catalizador> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                catalizadorService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Catalizador> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                catalizadorService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        catalizadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}