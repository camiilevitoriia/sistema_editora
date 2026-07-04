package br.ufrn.eaj.sistema_editora.repository;

import br.ufrn.eaj.sistema_editora.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}