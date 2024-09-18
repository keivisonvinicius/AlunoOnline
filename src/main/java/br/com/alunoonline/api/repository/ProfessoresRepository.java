package br.com.alunoonline.api.repository;

import br.com.alunoonline.api.model.Professores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessoresRepository extends JpaRepository<Professores, Long> {
}
