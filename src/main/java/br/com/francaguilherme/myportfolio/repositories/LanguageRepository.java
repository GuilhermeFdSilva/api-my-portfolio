package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para entidade {@link Language}. Este repositório fornece métodos para manipulação e persistência de dados
 * na tabela {@link Language}.
 *
 * @see Repository
 * @see Language
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
