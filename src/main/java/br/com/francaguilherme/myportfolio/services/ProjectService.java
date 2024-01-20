package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Admin;
import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *     Serviço para manipulação e tratamento de dados da entidade {@link Project}. Esta classe utiliza os métodos do
 *     repositório {@link ProjectRepository}, para ler, salvar, atualizar e deletar projetos.
 * </p>
 *
 * <p>
 *     Caso ocorra algum problema, essa classe pode lançar {@link EntityNotFoundException}.
 * </p>
 *
 * <p>
 *     {@link Service} é utilizado para que o Spring identifique que essa classe é um serviço, enquanto a anotação
 *     {@link Autowired} é utilizada para injeção de dependência do Spring, instanciando automaticamente
 *     {@link ProjectRepository} e {@link AdminService} que é utilizado para autentificação.
 * </p>
 *
 * @see Service
 * @see ProjectRepository
 * @see Project
 * @see AdminService
 * @see Autowired
 * @see EntityNotFoundException
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private AdminService adminService;

    /**
     * Retorna uma lista de todos os projetos armazenados no sistema.
     *
     * @return Lista de todos os projetos.
     */
    public List<Project> listProjects() {
        return repository.findAll();
    }

    /**
     * Salva um novo projeto no sistema. Antes disso, valida as credenciais fornecidas.
     *
     * @param project Projeto a ser salvo.
     * @param admin Credenciais administrativas.
     * @return O projeto salvo com seu ID.
     */
    public Project saveProject(@NonNull Project project, @NonNull Admin admin) {
        adminService.validatePassword(admin);

        return repository.save(project);
    }

    /**
     * Atualiza um projeto do sistema. Antes disso, valida as credenciais fornecidas.
     *
     * @param project Projeto atualizado com ID.
     * @param admin Credenciais administrativas.
     * @return O projeto atualizado.
     * @throws EntityNotFoundException Caso o ID dornecido seja inválido.
     */
    public Project updateProject(@NonNull Project project, @NonNull Admin admin) throws EntityNotFoundException {
        adminService.validatePassword(admin);
        Long id = project.getId();

        if (id != null && id > 0 && repository.existsById(id)) {
            return repository.save(project);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Deleta um projeto do sistema.
     *
     * @param id O ID do projeto a ser deletado. Antes disso, valida as credenciais fornecidas.
     * @param admin Credenciais administrativas.
     * @throws EntityNotFoundException Caso o ID fornecido seja inválido
     */
    public void deleteProject(@NonNull Long id, @NonNull Admin admin) throws EntityNotFoundException {
        adminService.validatePassword(admin);

        if (id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
