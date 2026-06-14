package eva2.eva2.service;

import eva2.eva2.model.TipoHechizo;
import eva2.eva2.repository.TipoHechizoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TipoHechizoService {

    @Autowired
    private TipoHechizoRepository tipoHechizoRepository;

    public List<TipoHechizo> findAll(){
        return tipoHechizoRepository.findAll();
    }

    public TipoHechizo findById(Integer id){
        return tipoHechizoRepository.findById(id).get();
    }

    public TipoHechizo save(TipoHechizo tipoHechizo){
        return tipoHechizoRepository.save(tipoHechizo);
    }

    public void delete(Integer id){
        tipoHechizoRepository.deleteById(id);
    }

    public TipoHechizo findByNombre(String nombre){
        return tipoHechizoRepository.findByNombre(nombre);
    }

}