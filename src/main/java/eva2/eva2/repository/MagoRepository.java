package eva2.eva2.repository;

import eva2.eva2.model.Mago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagoRepository extends JpaRepository<Mago, Integer> {

    Mago findByNombre(String nombre);

    Mago findByNivel(int nivel);

    Mago findByMana(int mana);

}