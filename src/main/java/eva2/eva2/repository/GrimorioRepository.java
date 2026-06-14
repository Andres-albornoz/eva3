package eva2.eva2.repository;

import eva2.eva2.model.Grimorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrimorioRepository extends JpaRepository<Grimorio, Integer> {

    Grimorio findByNombre(String nombre);

    Grimorio findByCapacidad(int capacidad);

}