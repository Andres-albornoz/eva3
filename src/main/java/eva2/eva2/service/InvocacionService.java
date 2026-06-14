package eva2.eva2.service;

import eva2.eva2.model.Invocacion;
import eva2.eva2.repository.InvocacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InvocacionService {

    @Autowired
    private InvocacionRepository invocacionRepository;

    public List<Invocacion> findAll(){
        return invocacionRepository.findAll();
    }

    public Invocacion findById(Integer id){
        return invocacionRepository.findById(id).get();
    }

    public Invocacion save(Invocacion invocacion){
        return invocacionRepository.save(invocacion);
    }

    public void delete(Integer id){
        invocacionRepository.deleteById(id);
    }

    public Invocacion findByNombre(String nombre){
        return invocacionRepository.findByNombre(nombre);
    }

    public Invocacion findByNivel(int nivel){
        return invocacionRepository.findByNivel(nivel);
    }

}