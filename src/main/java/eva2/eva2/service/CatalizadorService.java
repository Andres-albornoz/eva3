package eva2.eva2.service;

import eva2.eva2.model.Catalizador;
import eva2.eva2.repository.CatalizadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CatalizadorService {

    @Autowired
    private CatalizadorRepository catalizadorRepository;

    public List<Catalizador> findAll(){
        return catalizadorRepository.findAll();
    }

    public Catalizador findById(Integer id){
        return catalizadorRepository.findById(id).get();
    }

    public Catalizador save(Catalizador catalizador){
        return catalizadorRepository.save(catalizador);
    }

    public void delete(Integer id){
        catalizadorRepository.deleteById(id);
    }

    public Catalizador findByNombre(String nombre){
        return catalizadorRepository.findByNombre(nombre);
    }

    public Catalizador findByConsumible(boolean consumible){
        return catalizadorRepository.findByConsumible(consumible);
    }

}