package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.AdminNotFoundException;
import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidPasswordException;
import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Serviço para manipulação e validação de dados da entidade {@link Admin}. Essa classe utiliza os métodos do
 * repositório {@link AdminRepository}, para validar e atualizar a senha administrativa do sistema.
 * </p>
 *
 * <p>
 * Caso ocorra algum problema na validação de dados, essa classe pode lançar {@link InvalidLoginException}.
 * </p>
 *
 * <p>
 * {@link Service} é utilizado para que o Spring identifique que essa classe é um serviço, enquanto a anotação
 * {@link Autowired} é utilizada para injeção de dependência do Spring, instanciando automaticamente
 * {@link AdminRepository}.
 * </p>
 *
 * @see Service
 * @see AdminRepository
 * @see Admin
 * @see Autowired
 * @see InvalidLoginException
 */
@Service
public class AdminService {
    @Autowired
    private AdminRepository repository;

    /**
     * Valida a senha do administrador.
     *
     * @param admin Administrador que se deseja logar.
     * @throws InvalidLoginException Se ocorrer um erro durante a validação da senha.
     */
    public boolean validatePassword(String password) throws RuntimeException {
        Admin admin = repository.findById(1L).orElseThrow(AdminNotFoundException::new);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, admin.getPassword());
    }

    /**
     * Atualiza a senha do administrador.
     *
     * @param oldAdmin Credenciais antigas para validação.
     * @param newAdmin Novas credenciais.
     * @return O objeto {@link Admin} atualizado com a nova senha.
     * @throws InvalidLoginException Se ocorrer um erro durante a validação da senha.
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
