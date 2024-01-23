package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.InvalidLoginException;
import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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
    protected void validatePassword(@NonNull Admin admin) throws InvalidLoginException {
        Admin adminFound = repository.findAdminByLogin(admin.getLogin()).orElseThrow(InvalidLoginException::new);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(admin.getPassword(), adminFound.getPassword())) {
            return;
        }
        throw new InvalidLoginException();
    }

    /**
     * Atualiza as credenciais do administrador.
     *
     * @param oldAdmin Credenciais antigas para validação.
     * @param newAdmin Novas credenciais.
     * @throws InvalidLoginException Se ocorrer um erro durante a validação da senha.
     */
    public void setCredentials(@NonNull Admin oldAdmin, @NonNull Admin newAdmin) throws InvalidLoginException {
        validatePassword(oldAdmin);
        Admin mainAdmin = repository.findAdminByLogin(oldAdmin.getLogin()).orElseThrow(InvalidLoginException::new);

        mainAdmin.setPassword(new BCryptPasswordEncoder().encode(newAdmin.getPassword()));
        mainAdmin.setLogin(newAdmin.getLogin());
        repository.save(mainAdmin);
    }
}
