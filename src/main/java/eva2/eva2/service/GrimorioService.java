package eva2.eva2.service;

import eva2.eva2.model.Grimorio;
import eva2.eva2.repository.GrimorioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GrimorioService {

    @Autowired
    private GrimorioRepository grimorioRepository;

    public List<Grimorio> findAll(){
        return grimorioRepository.findAll();
    }

    public Grimorio findById(Integer id){
        return grimorioRepository.findById(id).get();
    }

    public Grimorio save(Grimorio grimorio){
        return grimorioRepository.save(grimorio);
    }

    public void delete(Integer id){
        grimorioRepository.deleteById(id);
    }

    public Grimorio findByNombre(String nombre){
        return grimorioRepository.findByNombre(nombre);
    }

    public Grimorio findByCapacidad(int capacidad){
        return grimorioRepository.findByCapacidad(capacidad);
    }

}