package eva2.eva2.service;

import eva2.eva2.model.Hechizo;
import eva2.eva2.model.Mago;
import eva2.eva2.repository.HechizoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class HechizoService {

    @Autowired
    private HechizoRepository HechizoRepository;

    @Autowired
    private MagoService magoService;

    public List<Hechizo> findAll(){
        return HechizoRepository.findAll();
    }

    public Hechizo findById(Integer id){
        return HechizoRepository.findById(id).get();
    }

    public Hechizo save(Hechizo hechizo){
        return HechizoRepository.save(hechizo);
    }

    public void delete(Integer id){
        HechizoRepository.deleteById(id);
    }

    public Hechizo findByAtributo(String atriburo){
        return HechizoRepository.findByAtributo(atriburo);
    }

    public Hechizo findByNivel(int nivel){
        return HechizoRepository.findByNivel(nivel);
    }

    public Hechizo findByEnPos(boolean enPos){
        return HechizoRepository.findByEnPos(enPos);
    }

    public String lanzarHechizo(Integer magoId, Integer hechizoId) {
        Mago mago = magoService.findById(magoId);
        Hechizo hechizo = findById(hechizoId);

        if (mago.getNivel() < hechizo.getNivel()) {
            return "El mago " + mago.getNombre() + " no tiene suficiente nivel para lanzar " + hechizo.getNombre();
        }

        if (mago.getMana() < 10) {
            return "El mago " + mago.getNombre() + " no tiene suficiente mana";
        }

        mago.setMana(mago.getMana() - 10);
        magoService.save(mago);

        return "El mago " + mago.getNombre() + " lanzó " + hechizo.getNombre() + " exitosamente. Mana restante: " + mago.getMana();
    }

}
