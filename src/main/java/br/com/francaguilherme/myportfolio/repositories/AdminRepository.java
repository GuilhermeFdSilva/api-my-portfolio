package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link Admin}. Este repositório fornece métodos para manipulação e persistência de dados na
 * tabela {@link Admin}.
 *
 * @see Repository
 * @see Admin
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
