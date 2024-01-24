package br.com.francaguilherme.myportfolio.helpers.initializer;

import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     Essa classe é responsável por inicializar dados necessários dentro do banco, caso não tenham sido inicializados.
 * </p>
 *
 * <p>
 *     Essa classe utiliza a anotação {@link Component} para indicar ao Spring que essa se trata de uma classe de
 *     configuração
 * </p>
 *
 * @see Component
 * @see CommandLineRunner
 */
@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private AdminRepository repository;

    @Override
    public void run(String... args) {
        if (!repository.existsById(1L)) {
            Admin admin = new Admin();
            admin.setLogin("admin");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            repository.save(admin);
        }
    }
}
