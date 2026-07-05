package br.ufrn.eaj.sistema_editora.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_editora")
@Data
@NoArgsConstructor
@Audited
public class Editora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cnpj;

    @OneToMany(mappedBy = "editora")
    private List<Livro> livros = new ArrayList<>();
}
