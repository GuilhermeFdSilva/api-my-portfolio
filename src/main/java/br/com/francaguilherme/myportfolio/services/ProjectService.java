package br.com.francaguilherme.myportfolio.services;

import br.com.francaguilherme.myportfolio.models.Project;
import br.com.francaguilherme.myportfolio.repositories.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para operações relacionadas a entidade {@link Project}
 * Este serviço fornece métodos para:
 *  - Listar os projetos;
 *  - Salvar um projeto;
 *  - Atualizar um projeto;
 *  - Deletar um projeto.
 */
@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;

    /**
     * Retorna uma lista de todos os projetos armazenados no sistema.
     *
     * @return Lista de todos os projetos.
     */
    public List<Project> listProjects() {
        return repository.findAll();
    }

    /**
     * Salva um novo projeto.
     *
     * @param project Projeto a ser salvo.
     * @return O projeto salvo com seu ID.
     */
    public Project saveProject(Project project) {
        return repository.save(project);
    }

    /**
     * Atualiza um projeto do sistema.
     *
     * @param project Projeto atualizado com ID.
     * @return O projeto atualizado.
     */
    public Project updateProject(Project project) {
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
     * @param id O ID do projeto a ser deletado.
     */
    public void deleteProject(Long id) {
        if (id != null && id > 0 && repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
