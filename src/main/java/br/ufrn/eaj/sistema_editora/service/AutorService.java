package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.Autor;
import br.ufrn.eaj.sistema_editora.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository repository;

    @Transactional(readOnly = true)
    public List<Autor> buscarTodos() { return repository.findAll(); }

    @Transactional
    public Autor salvar(Autor autor) { return repository.save(autor); }
}