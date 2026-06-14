package eva2.eva2.service;

import eva2.eva2.model.Elemento;
import eva2.eva2.repository.ElementoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ElementoService {

    @Autowired
    private ElementoRepository elementoRepository;

    public List<Elemento> findAll(){
        return elementoRepository.findAll();
    }

    public Elemento findById(Integer id){
        return elementoRepository.findById(id).get();
    }

    public Elemento save(Elemento elemento){
        return elementoRepository.save(elemento);
    }

    public void delete(Integer id){
        elementoRepository.deleteById(id);
    }

    public Elemento findByNombre(String nombre){
        return elementoRepository.findByNombre(nombre);
    }

    public Elemento findByDescripcion(String descripcion){
        return elementoRepository.findByDescripcion(descripcion);
    }

}