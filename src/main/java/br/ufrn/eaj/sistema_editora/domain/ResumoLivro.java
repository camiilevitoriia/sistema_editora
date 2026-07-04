package br.ufrn.eaj.sistema_editora.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tb_resumo_livro")
@Data
@NoArgsConstructor
@Audited
public class ResumoLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String texto;

    // Relacionamento 1:1
    @OneToOne(mappedBy = "resumo")
    private Livro livro;
}