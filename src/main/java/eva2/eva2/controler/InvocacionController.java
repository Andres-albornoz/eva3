package eva2.eva2.controler;

import eva2.eva2.model.Invocacion;
import eva2.eva2.service.InvocacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Invocaciones")
public class InvocacionController {

    @Autowired
    private InvocacionService invocacionService;

    @PostMapping
    public ResponseEntity<Invocacion> crear(@RequestBody Invocacion invocacion){
        return ResponseEntity.ok(
                invocacionService.save(invocacion)
        );
    }

    @GetMapping
    public ResponseEntity<List<Invocacion>> mostrar(){
        return ResponseEntity.ok(
                invocacionService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invocacion> buscar(@PathVariable Integer id){
        return ResponseEntity.ok(
                invocacionService.findById(id)
        );
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Invocacion> buscarNombre(@PathVariable String nombre){
        return ResponseEntity.ok(
                invocacionService.findByNombre(nombre)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invocacion> actualizar(
            @PathVariable Integer id,
            @RequestBody Invocacion invocacion){

        Invocacion i = invocacionService.findById(id);

        i.setNombre(invocacion.getNombre());
        i.setNivel(invocacion.getNivel());

        return ResponseEntity.ok(
                invocacionService.save(i)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Integer id){
        invocacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}