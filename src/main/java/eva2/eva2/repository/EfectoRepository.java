package eva2.eva2.repository;

import eva2.eva2.model.Efecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EfectoRepository extends JpaRepository<Efecto, Integer> {

    Efecto findByNombre(String nombre);

    Efecto findByPotencia(int potencia);

}