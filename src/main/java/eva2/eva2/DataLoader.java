package eva2.eva2;

import eva2.eva2.model.*;
import eva2.eva2.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private HechizoRepository hechizoRepository;

    @Autowired
    private MagoRepository magoRepository;

    @Autowired
    private GrimorioRepository grimorioRepository;

    @Autowired
    private ElementoRepository elementoRepository;

    @Autowired
    private EscuelaMagicaRepository escuelaMagicaRepository;

    @Autowired
    private TipoHechizoRepository tipoHechizoRepository;

    @Autowired
    private EfectoRepository efectoRepository;

    @Autowired
    private CatalizadorRepository catalizadorRepository;

    @Autowired
    private EncantamientoRepository encantamientoRepository;

    @Autowired
    private InvocacionRepository invocacionRepository;

    @Override
    public void run(String... args) {

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {

            Hechizo hechizo = new Hechizo();
            hechizo.setNombre(faker.book().title());
            hechizo.setAtributo(faker.options().option("Fuego", "Agua", "Viento", "Tierra"));
            hechizo.setNivel(random.nextInt(10));
            hechizo.setEnPos(random.nextBoolean());
            hechizoRepository.save(hechizo);

            Mago mago = new Mago();
            mago.setNombre(faker.funnyName().name());
            mago.setNivel(random.nextInt(10) + 1);
            mago.setMana(random.nextInt(500));
            magoRepository.save(mago);

            Grimorio grimorio = new Grimorio();
            grimorio.setNombre("Grimorio " + faker.book().title());
            grimorio.setCapacidad(random.nextInt(50) + 1);
            grimorioRepository.save(grimorio);

            Elemento elemento = new Elemento();
            elemento.setNombre(faker.options().option("Fuego", "Agua", "Viento", "Tierra"));
            elemento.setDescripcion("Elemento mágico");
            elementoRepository.save(elemento);

            EscuelaMagica escuela = new EscuelaMagica();
            escuela.setNombre("Escuela " + faker.book().genre());
            escuela.setEspecialidad("Magia General");
            escuelaMagicaRepository.save(escuela);

            TipoHechizo tipo = new TipoHechizo();
            tipo.setNombre(faker.options().option("Ataque", "Defensa", "Apoyo", "Curación"));
            tipoHechizoRepository.save(tipo);

            Efecto efecto = new Efecto();
            efecto.setNombre("Efecto " + faker.animal().name());
            efecto.setPotencia(random.nextInt(100));
            efectoRepository.save(efecto);

            Catalizador catalizador = new Catalizador();
            catalizador.setNombre("Catalizador " + faker.color().name());
            catalizador.setConsumible(random.nextBoolean());
            catalizadorRepository.save(catalizador);

            Encantamiento encantamiento = new Encantamiento();
            encantamiento.setNombre("Encantamiento " + faker.book().title());
            encantamiento.setNivel(random.nextInt(10));
            encantamientoRepository.save(encantamiento);

            Invocacion invocacion = new Invocacion();
            invocacion.setNombre("Invocación " + faker.funnyName().name());
            invocacion.setNivel(random.nextInt(10));
            invocacionRepository.save(invocacion);
        }
    }
}