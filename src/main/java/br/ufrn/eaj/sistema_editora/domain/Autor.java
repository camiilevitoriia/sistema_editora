package br.ufrn.eaj.sistema_editora.domain;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_autor")
@Data
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nacionalidade;

    // Relacionamento N:M
    @ManyToMany(mappedBy = "autores")
    private List<Livro> livros = new ArrayList<>();
}
