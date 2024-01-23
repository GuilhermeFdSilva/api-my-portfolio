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

    public static List<ProjectDTO> listToDTO(List<Project> projects) {
        return projects.stream().map(ProjectDTO::new).toList();
    }

    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(project);
    }
}
