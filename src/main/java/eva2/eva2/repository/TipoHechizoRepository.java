package eva2.eva2.repository;

import eva2.eva2.model.TipoHechizo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoHechizoRepository extends JpaRepository<TipoHechizo, Integer> {

    TipoHechizo findByNombre(String nombre);

}