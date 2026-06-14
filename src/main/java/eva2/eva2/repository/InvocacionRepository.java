package eva2.eva2.repository;

import eva2.eva2.model.Invocacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvocacionRepository extends JpaRepository<Invocacion, Integer> {

    Invocacion findByNombre(String nombre);

    Invocacion findByNivel(int nivel);

}