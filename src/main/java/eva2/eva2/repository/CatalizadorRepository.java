package eva2.eva2.repository;

import eva2.eva2.model.Catalizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalizadorRepository extends JpaRepository<Catalizador, Integer> {

    Catalizador findByNombre(String nombre);

    Catalizador findByConsumible(boolean consumible);

}