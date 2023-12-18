package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
