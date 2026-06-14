package eva2.eva2.service;

import eva2.eva2.model.EscuelaMagica;
import eva2.eva2.repository.EscuelaMagicaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EscuelaMagicaService {

    @Autowired
    private EscuelaMagicaRepository escuelaMagicaRepository;

    public List<EscuelaMagica> findAll(){
        return escuelaMagicaRepository.findAll();
    }

    public EscuelaMagica findById(Integer id){
        return escuelaMagicaRepository.findById(id).get();
    }

    public EscuelaMagica save(EscuelaMagica escuela){
        return escuelaMagicaRepository.save(escuela);
    }

    public void delete(Integer id){
        escuelaMagicaRepository.deleteById(id);
    }

    public EscuelaMagica findByNombre(String nombre){
        return escuelaMagicaRepository.findByNombre(nombre);
    }

    public EscuelaMagica findByEspecialidad(String especialidad){
        return escuelaMagicaRepository.findByEspecialidad(especialidad);
    }

}