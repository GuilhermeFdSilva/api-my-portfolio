package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Comment;
import br.com.francaguilherme.myportfolio.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para entidade {@link Comment}. Este repositório fornece métodos para manipulação e persistência de dados
 * na tabela {@link Comment}.
 *
 * @see Repository
 * @see Comment
 * @see Project
 * @see Query
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * Lista todos os comentários de um projeto({@link Project}).
     *
     * @param projectId ID do projeto buscado.
     * @return A lista dos comentários do projeto.
     */
    @Query("SELECT c FROM Comment c WHERE c.project.id = :projectId")
    List<Comment> findByProjectId(Long projectId);
}
