package eva2.eva2.controler;

import eva2.eva2.model.Mago;
import eva2.eva2.service.MagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Magos")
public class MagoController {

    @Autowired
    private MagoService magoService;

    @PostMapping
    public ResponseEntity<Mago> crear(@RequestBody Mago mago){
        return ResponseEntity.ok(
                magoService.save(mago)
        );
    }

    @GetMapping
    public ResponseEntity<List<Mago>> mostrar(){
        return ResponseEntity.ok(
                magoService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mago> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                magoService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Mago> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                magoService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        magoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}