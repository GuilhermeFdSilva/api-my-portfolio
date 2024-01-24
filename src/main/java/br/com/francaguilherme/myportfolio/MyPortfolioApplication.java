package br.com.francaguilherme.myportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 *     Classe principal que inicia a aplicação.
 * </p>
 *
 * <p>
 *     A classe utiliza a anotação {@link SpringBootApplication} para indicar ao SpringBoot que esta é uma classe de
 *     configuração, e ponto de entrada para a aplicação.
 * </p>
 *
 * @see SpringApplication
 * @see SpringBootApplication
 */
@SpringBootApplication
public class MyPortfolioApplication {

    /**
     * <p>
     *     Ponto de entrada para a aplicação.
     * </p>
     *
     * <p>
     *      O método inicia a aplicação e chama o método {@link SpringApplication#run(Class, String...)}. Para esse
     *      método são passados como argumentos a propria {@link MyPortfolioApplication}, para configuração do Spring.
     * </p>
     *
     * @param args Argumentos de linha de comando fornecidos ao iniciar a aplicação.
     */
    public static void main(String[] args) {
        SpringApplication.run(MyPortfolioApplication.class, args);
    }
}
