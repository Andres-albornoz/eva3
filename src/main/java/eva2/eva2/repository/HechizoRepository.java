package eva2.eva2.repository;

import eva2.eva2.model.Hechizo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HechizoRepository extends JpaRepository<Hechizo, Integer> {

    Hechizo findByAtributo(String atributo);

    Hechizo findByNivel(int nivel);

    Hechizo findByEnPos(boolean enPos);
}
