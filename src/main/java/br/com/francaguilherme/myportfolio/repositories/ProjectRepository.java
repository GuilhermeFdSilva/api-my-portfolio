package br.com.francaguilherme.myportfolio.repositories;

import br.com.francaguilherme.myportfolio.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
