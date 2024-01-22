package br.com.francaguilherme.myportfolio.helpers.initializer;

import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Inicializador de dados para a aplicação.
 * Realiza a inicialização de um usuário administrador se ele ainda não existir.
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
