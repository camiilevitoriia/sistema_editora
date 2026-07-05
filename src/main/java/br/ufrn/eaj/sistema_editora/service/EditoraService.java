package br.ufrn.eaj.sistema_editora.service;

import br.ufrn.eaj.sistema_editora.domain.Editora;
import br.ufrn.eaj.sistema_editora.dto.EditoraRequestDTO;
import br.ufrn.eaj.sistema_editora.dto.EditoraResponseDTO;
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
    public List<EditoraResponseDTO> buscarTodos() {
        return repository.findAll().stream()
                .map(EditoraResponseDTO::fromEntity)
                .toList();
    }

    @Transactional
    public EditoraResponseDTO salvar(EditoraRequestDTO dto) {
        Editora editora = new Editora();
        editora.setNome(dto.nome());
        editora.setCnpj(dto.cnpj());

        return EditoraResponseDTO.fromEntity(repository.save(editora));
    }
}
