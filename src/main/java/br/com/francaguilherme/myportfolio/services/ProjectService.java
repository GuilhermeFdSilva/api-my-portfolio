package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.helpers.exceptions.EmptyListException;
import br.com.francaguilherme.myportfolio.models.DTOs.ProjectDTO;
import br.com.francaguilherme.myportfolio.models.entities.Admin;
import br.com.francaguilherme.myportfolio.models.entities.Language;
import br.com.francaguilherme.myportfolio.models.entities.Project;
import br.com.francaguilherme.myportfolio.repositories.LanguageRepository;
import br.com.francaguilherme.myportfolio.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *     Serviço para manipulação e tratamento de dados da entidade {@link Project} atraves da DTO {@link ProjectDTO}.
 *     Esta classe utiliza os métodos do repositório {@link ProjectRepository}, para ler, salvar, atualizar e deletar projetos.
 * </p>
 *
 * <p>
 *     Caso ocorra algum problema, essa classe pode lançar {@link EntityNotFoundException} ou {@link EmptyListException}.
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
 * @see ProjectDTO
 * @see AdminService
 * @see Autowired
 * @see EntityNotFoundException
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private AdminService adminService;

    /**
     * Retorna uma lista de todos os projetos armazenados no sistema.
     *
     * @return Lista de todos os projetos.
     * @throws EmptyListException Caso não haja nenhum projeto listado.
     */
    public List<ProjectDTO> listProjects() throws EmptyListException {
        List<ProjectDTO> projects = ProjectDTO.listToDTO(repository.findAll());

        if (projects.isEmpty()) {
            throw new EmptyListException();
        }

        return projects;
    }

    /**
     * Salva um novo projeto no sistema. Antes disso, valida as credenciais fornecidas.
     *
     * @param dto {@link ProjectDTO} representando o projeto a ser salvo.
     * @param admin Credenciais administrativas.
     * @return O projeto salvo com seu ID.
     */
    public ProjectDTO saveProject(@NonNull ProjectDTO dto, @NonNull Admin admin) {
        adminService.validatePassword(admin);

        Language language = languageRepository.findById(dto.getMain_language_id()).orElseThrow(EntityNotFoundException::new);

        Project project = new Project(dto, language);

        return ProjectDTO.toDTO(repository.save(project));
    }

    /**
     * Atualiza um projeto do sistema. Antes disso, valida as credenciais fornecidas.
     *
     * @param dto {@link ProjectDTO} o projeto atualizado com ID.
     * @param admin Credenciais administrativas.
     * @return O projeto atualizado.
     * @throws EntityNotFoundException Caso o ID dornecido seja inválido.
     */
    public ProjectDTO updateProject(@NonNull ProjectDTO dto, @NonNull Admin admin) throws EntityNotFoundException {
        adminService.validatePassword(admin);
        Long id = dto.getId();

        if (id != null && id > 0 && repository.existsById(id)) {
            Language language = languageRepository.findById(id).orElseThrow(EntityNotFoundException::new);

            Project project = new Project(dto, language);

            return ProjectDTO.toDTO(repository.save(project));
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Like ou dislike a um projeto de acordo com o tipo de voto(voteType).
     *
     * @param dto {@link ProjectDTO} do projeto alvo.
     * @param voteType Tipo de voto:
     *                 <ul>
     *                     <li>like</li>
     *                     <li>dislike</li>
     *                 </ul>
     * @return O projeto com o número de likes atualizado.
     * @throws EntityNotFoundException Caso o projeto seja inválido.
     */
    public ProjectDTO voteProject(@NonNull ProjectDTO dto, @NonNull String voteType) throws EntityNotFoundException {
        Project projectOnDB = repository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);

        // De acordo com o voteType dornecido, sera chamado o método adequado.
        if ("like".equals(voteType)) {
            projectOnDB.likeProject();
        } else if ("dislike".equals(voteType)) {
            projectOnDB.dislikeProject();
        }

        return ProjectDTO.toDTO(repository.save(projectOnDB));
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
