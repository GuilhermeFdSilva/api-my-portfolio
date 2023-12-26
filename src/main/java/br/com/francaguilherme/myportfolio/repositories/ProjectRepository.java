package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para entidade {@link Project}.
 * Este repositório fornece métodos para interagir com a entidade relacionada,
 * para a persistência dos dados.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
