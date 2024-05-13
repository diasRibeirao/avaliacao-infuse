package br.com.infuse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Avaliação - Programador Java")
                        .version("1.0.0")
                        .contact(new Contact()
                                .email("emersondiaspd@gmail.com")
                                .name("Emerson Dias de Oliveira"))
                        .license(new License()
                                    .name("Unlicense")
                                    .url("https://unlicense.org/")));
    }

}
