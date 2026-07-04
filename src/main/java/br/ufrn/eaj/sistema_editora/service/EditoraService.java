package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.Editora;
import br.ufrn.eaj.sistema_editora.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository repository;

    @Transactional(readOnly = true)
    public List<Editora> buscarTodos() { return repository.findAll(); }

    @Transactional
    public Editora salvar(Editora editora) { return repository.save(editora); }
}