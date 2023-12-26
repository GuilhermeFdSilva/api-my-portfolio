package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para entidade {@link Language}.
 * Este repositório fornece métodos para interagir com a entidade relacionada,
 * para a persistência dos dados.
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
