package eva2.eva2.repository;

import eva2.eva2.model.Elemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementoRepository extends JpaRepository<Elemento, Integer> {

    Elemento findByNombre(String nombre);

    Elemento findByDescripcion(String descripcion);

}