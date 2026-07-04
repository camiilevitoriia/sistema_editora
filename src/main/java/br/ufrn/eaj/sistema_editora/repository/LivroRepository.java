package br.ufrn.eaj.sistema_editora.repository;

import br.ufrn.eaj.sistema_editora.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
