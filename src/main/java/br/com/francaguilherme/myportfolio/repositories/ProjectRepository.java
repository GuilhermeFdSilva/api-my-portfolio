package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para entidade {@link Project}. Este repositório fornece métodos para manipulação e persistência de dados
 * na tabela {@link Project}.
 *
 * @see Repository
 * @see Project
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
