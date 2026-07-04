package br.ufrn.eaj.sistema_editora.repository;

import br.ufrn.eaj.sistema_editora.domain.ResumoLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumoLivroRepository extends JpaRepository<ResumoLivro, Long> {
}