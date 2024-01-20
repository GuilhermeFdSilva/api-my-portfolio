package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório da entidade {@link Admin}. Este repositório fornece métodos para manipulação e persistência de dados na
 * tabela {@link Admin}.
 *
 * @see Repository
 * @see Admin
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    /**
     * Encontra um Administrador pelo login.
     *
     * @param login Login para busca.
     * @return Um {@link Optional} de {@link Admin}.
     */
    @Query("SELECT a FROM Admin a WHERE a.login = :login")
    Optional<Admin> findAdminByLogin(String login);
}
