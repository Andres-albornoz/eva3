package eva2.eva2.repository;

import eva2.eva2.model.EscuelaMagica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EscuelaMagicaRepository extends JpaRepository<EscuelaMagica, Integer> {

    EscuelaMagica findByNombre(String nombre);

    EscuelaMagica findByEspecialidad(String especialidad);

}