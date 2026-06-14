package eva2.eva2.service;

import eva2.eva2.model.Efecto;
import eva2.eva2.repository.EfectoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EfectoService {

    @Autowired
    private EfectoRepository efectoRepository;

    public List<Efecto> findAll(){
        return efectoRepository.findAll();
    }

    public Efecto findById(Integer id){
        return efectoRepository.findById(id).get();
    }

    public Efecto save(Efecto efecto){
        return efectoRepository.save(efecto);
    }

    public void delete(Integer id){
        efectoRepository.deleteById(id);
    }

    public Efecto findByNombre(String nombre){
        return efectoRepository.findByNombre(nombre);
    }

    public Efecto findByPotencia(int potencia){
        return efectoRepository.findByPotencia(potencia);
    }

}