package eva2.eva2.service;

import eva2.eva2.model.Mago;
import eva2.eva2.repository.MagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MagoService {

    @Autowired
    private MagoRepository magoRepository;

    public List<Mago> findAll(){
        return magoRepository.findAll();
    }

    public Mago findById(Integer id){
        return magoRepository.findById(id).get();
    }

    public Mago save(Mago mago){
        return magoRepository.save(mago);
    }

    public void delete(Integer id){
        magoRepository.deleteById(id);
    }

    public Mago findByNombre(String nombre){
        return magoRepository.findByNombre(nombre);
    }

    public Mago findByNivel(int nivel){
        return magoRepository.findByNivel(nivel);
    }

    public Mago findByMana(int mana){
        return magoRepository.findByMana(mana);
    }

}