package eva2.eva2.repository;

import eva2.eva2.model.Encantamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncantamientoRepository extends JpaRepository<Encantamiento, Integer> {

    Encantamiento findByNombre(String nombre);

    Encantamiento findByNivel(int nivel);

}