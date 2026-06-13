package eva2.eva2;

import eva2.eva2.model.*;
import eva2.eva2.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Random;
//@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private HechizoRepository hechizoRepository;

    @Override
    public void run(String... args) {

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {

            Hechizo hechizo = new Hechizo();

            hechizo.setNombre(faker.book().genre());
            hechizo.setAtributo(faker.book().genre());
            hechizo.setNivel(random.nextInt(10));
            hechizo.setEnPos(random.nextBoolean());

            hechizoRepository.save(hechizo);
        }
    }
}