package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.Autor;
import br.ufrn.eaj.sistema_editora.dto.AutorRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.AutorResponseDTO;
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
    public List<AutorResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(AutorResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public AutorResponseDTO salvar(AutorRequestDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.nome());
        autor.setNacionalidade(dto.nacionalidade());

        return AutorResponseDTO.fromEntity(repository.save(autor));
    }
}
