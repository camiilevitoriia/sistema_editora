package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.Livro;
import br.ufrn.eaj.sistema_editora.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    @Transactional(readOnly = true)
    public List<Livro> buscarTodos() { return repository.findAll(); }

    @Transactional
    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }
}