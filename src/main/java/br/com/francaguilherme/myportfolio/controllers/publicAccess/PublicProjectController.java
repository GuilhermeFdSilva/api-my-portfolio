package br.com.francaguilherme.myportfolio.controllers.publicAccess;

import br.com.francaguilherme.myportfolio.models.DTOs.ProjectDTO;
import br.com.francaguilherme.myportfolio.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *     Classe com endpoints públicos para manipulação da DTO {@link ProjectDTO}. Essa classe retorna a
 *     {@link ResponseEntity} com ao menos um objeto {@link ProjectDTO} e o {@link HttpStatus} adequado para a
 *     requisição.
 * </p>
 *
 * <p>
 *     Essa classe utiliza as anotações {@link RestController} para mostrar ao Spring que essa classe se trata de um
 *     Controller, e {@link RequestMapping} para definir o path padrão da classe como /public/projects.
 * </p>
 *
 * <p>
 *     Métodos presentes na classe:
 *     <ul>
 *         <li>{@link #listProjects()}: Lista todos os projetos do sistema;</li>
 *         <li>{@link #}: Avalia um projeto.</li>
 *     </ul>
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 * @see GetMapping
 * @see PutMapping
 */
@RestController
@RequestMapping("/public/projects")
public class PublicProjectController {
    @Autowired
    private ProjectService service;

    /**
     * Lista todos os projetos do sistema.
     *
     * @return {@link ResponseEntity} com {@link HttpStatus#OK} e a lista dos projetos.
     */
    @GetMapping
    public ResponseEntity<List<ProjectDTO>> listProjects() {
        List<ProjectDTO> projects = service.listProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /**
     * Avalia um projeto, positivamente ou negativamente.
     *
     * @param voteType Tipo de voto:
     *                 <ul>
     *                     <li>like</li>
     *                     <li>dislike</li>
     *                 </ul>
     * @param project Projeto alvo do voto.
     * @return {@link ResponseEntity} com {@link HttpStatus#OK} e o projeto com o número de votos atualizado.
     */
    @PutMapping("/{voteType}")
    public ResponseEntity<ProjectDTO> voteProject(@PathVariable String voteType, @Valid @RequestBody ProjectDTO project) {
        ProjectDTO projectVoted = service.voteProject(project, voteType);
        return new ResponseEntity<>(projectVoted, HttpStatus.OK);
    }
}
