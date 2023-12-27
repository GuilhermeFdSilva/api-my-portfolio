package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.AdminNotFoundException;
import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidPasswordException;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Serviço para operações relacionadas a entidade {@link Admin}.
 * Este serviço fornece métodos para validar e atualizar a senha do administrador.
 */
@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    /**
     * Valida a senha do administrador.
     *
     * @param password Senha a ser validada.
     * @return {@code true} caso a senha seja válida, {@code false} caso contrario
     * @throws RuntimeException Se ocorrer um erro durante a validação da senha.
     */
    public boolean validatePassword(String password) throws RuntimeException {
        Admin admin = repository.findById(1L).orElseThrow(AdminNotFoundException::new);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, admin.getPassword());
    }

    /**
     * Atualiza a senha do administrador.
     *
     * @param oldPassword A senha antida do administrador.
     * @param newPassword A nova senha a ser definida.
     * @return O objeto {@link Admin} atualizado com a nova senha.
     * @throws RuntimeException Se a senha antiga não for válida.
     */
    public Admin setPassword(String oldPassword, String newPassword) throws RuntimeException {
        if (validatePassword(oldPassword)) {
            Admin mainAdmin = repository.findById(1L).orElseThrow(AdminNotFoundException::new);

            mainAdmin.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            return repository.save(mainAdmin);
        } else {
            throw new InvalidPasswordException();
        }
    }
}
