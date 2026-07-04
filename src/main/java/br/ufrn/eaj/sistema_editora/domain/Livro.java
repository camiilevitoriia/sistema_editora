package br.ufrn.eaj.sistema_editora.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tb_livro")
@Data
@NoArgsConstructor
@Audited
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;
    private Integer anoPublicacao;

    // Relacionamento N:1
    @ManyToOne
    @JoinColumn(name = "editora_id")
    private Editora editora;

    // Relacionamento 1:1
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resumo_id")
    private ResumoLivro resumo;

    // Relacionamento N:M
    @ManyToMany
    @JoinTable(
            name = "tb_livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();
}
