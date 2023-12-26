package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para entidade {@link Admin}.
 * Este repositório fornece métodos para interagir com a entidade relacionada,
 * para a persistência dos dados.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
