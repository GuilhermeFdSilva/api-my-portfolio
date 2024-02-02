package br.com.francaguilherme.myportfolio.models.DTOs;

import br.com.francaguilherme.myportfolio.models.entities.Project;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 *     Objeto de transferência de dados (DTO) de {@link Project}. Esta classe é utilizada para manipulação e exibição de
 *     dados relacionados a entidade {@link Project}.
 * </p>
 *
 * <p>
 *     Ela utiliza as anotações {@link Getter}, {@link Setter} e {@link NoArgsConstructor} do {@link lombok} para
 *     gerar automaticamente os getters e setters de todos os atributos da classe e um construtor sem argumentos.
 * </p>
 *
 * <p>
 *     Os atributos que essa classe utiliza são:
 *     <ul>
 *         <li>{@link #id}: Identificador do projeto;</li>
 *         <li>{@link #title}: Título/ nome do projeto;</li>
 *         <li>{@link #image}: Link para a imagem do projeto;</li>
 *         <li>{@link #description}: Descrição do projeto;</li>
 *         <li>{@link #main_language_id}: ID da principal linguagem do projeto;</li>
 *         <li>{@link #tools}: Ferramentas utilizadas no projeto;</li>
 *         <li>{@link #link_gh}: Link para o GitHub do projeto;</li>
 *         <li>{@link #link_pg}: Link para página do projeto, caso tenha;</li>
 *         <li>{@link #readme}: README.md do projeto;</li>
 *         <li>{@link #likes}: Quantidade de likes do projeto.</li>
 *     </ul>
 * </p>
 *
 * @see Project
 * @see Getter
 * @see Setter
 * @see NoArgsConstructor
 * @see lombok
 */
@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
    private Long id;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String title;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String image;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String description;

    @JsonProperty("main_language")
    @NotNull(message = "Não pode ser nulo")
    private Long main_language_id;

    @NotNull(message = "Não pode ser nulo")
    private byte[] readme;

    @NotNull(message = "Não pode ser nulo")
    @NotBlank(message = "Não pode estar em branco")
    private String link_gh;

    private String link_pg;

    private String[] tools;

    private int likes;

    /**
     * Construtor para inicialização de um objeto a partir de um {@link Project}.
     *
     * @param project {@link Project} contendo os dados para inicialização.
     */
    public ProjectDTO(@NonNull Project project) {
        this.id = project.getId();
        this.title = project.getTitle();
        this.image = project.getImage();
        this.description = project.getDescription();
        this.main_language_id = project.getMain_language().getId();
        this.readme = project.getReadme();
        this.link_gh = project.getLink_gh();
        this.link_pg = project.getLink_pg();
        this.tools = project.getTools();
        this.likes = project.getLikes();
    }

    /**
     * Função estática para conversão de um objeto {@link Project} em um objeto {@link ProjectDTO}.
     *
     * @param project {@link Project} a ser convertido.
     * @return Objeto convertido para {@link ProjectDTO}.
     */
    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(project);
    }

    /**
     * Função estática para conversão de uma lista de {@link Project} em uma lista de {@link ProjectDTO}.
     *
     * @param projects Lista de {@link Project} para conversão.
     * @return Lista com objetos convertidos para {@link ProjectDTO}.
     */
    public static List<ProjectDTO> listToDTO(List<Project> projects) {
        return projects.stream().map(ProjectDTO::new).toList();
    }
}
