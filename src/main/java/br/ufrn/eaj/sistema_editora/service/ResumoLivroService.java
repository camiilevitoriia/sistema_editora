package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.ResumoLivro;
import br.ufrn.eaj.sistema_editora.dto.ResumoLivroRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.ResumoLivroResponseDTO;
import br.ufrn.eaj.sistema_editora.repository.ResumoLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumoLivroService {
    @Autowired
    private ResumoLivroRepository repository;

    @Transactional
    public ResumoLivroResponseDTO salvar(ResumoLivroRequestDTO dto) {
        ResumoLivro resumo = new ResumoLivro();
        resumo.setTexto(dto.texto());

        return ResumoLivroResponseDTO.fromEntity(repository.save(resumo));
    }
}
