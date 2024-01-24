package br.com.francaguilherme.myportfolio.helpers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *     Classe de configurações do Srpring. Essa classe configura quais são as origens que podem acessar os endpoints da
 *     aplicação.
 * </p>
 *
 * <p>
 *     Essa classe utiliza a anotação {@link Configuration} para indicar ao Spring que o conteúdo dessa classe trata-se
 *     de uma configuração para {@link EnableWebMvc}.
 * </p>
 *
 * @see Configuration
 * @see EnableWebMvc
 * @see WebMvcConfigurer
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://francaguilherme.com.br")
                .allowedMethods("GET", "POST", "PUT")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
