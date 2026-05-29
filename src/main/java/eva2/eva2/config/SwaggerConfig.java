package eva2.eva2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOPEAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API creacion de hechizos")
                        .version("0.2")
                        .description("documentacion del micro servicio para la 'creacion y registro de hechizos' "));
    }
}
