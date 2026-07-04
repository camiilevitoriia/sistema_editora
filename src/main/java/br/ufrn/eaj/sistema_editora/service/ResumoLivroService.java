package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.ResumoLivro;
import br.ufrn.eaj.sistema_editora.repository.ResumoLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumoLivroService {
    @Autowired
    private ResumoLivroRepository repository;

    @Transactional
    public ResumoLivro salvar(ResumoLivro resumo) { return repository.save(resumo); }
}