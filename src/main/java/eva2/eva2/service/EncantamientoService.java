package eva2.eva2.service;

import eva2.eva2.model.Encantamiento;
import eva2.eva2.repository.EncantamientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EncantamientoService {

    @Autowired
    private EncantamientoRepository encantamientoRepository;

    public List<Encantamiento> findAll(){
        return encantamientoRepository.findAll();
    }

    public Encantamiento findById(Integer id){
        return encantamientoRepository.findById(id).get();
    }

    public Encantamiento save(Encantamiento encantamiento){
        return encantamientoRepository.save(encantamiento);
    }

    public void delete(Integer id){
        encantamientoRepository.deleteById(id);
    }

    public Encantamiento findByNombre(String nombre){
        return encantamientoRepository.findByNombre(nombre);
    }

    public Encantamiento findByNivel(int nivel){
        return encantamientoRepository.findByNivel(nivel);
    }

}