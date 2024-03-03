package io.github.samleticias.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
// compilação de getter, setter, constructor required, tostring, equalsandhashcode
@Entity
@Table(name = "produto")
// compilador vai compilar classe e gerar getter e setter no codigo compilado
// biblioteca do lombok faz isso e plugin serve pra ide reconhecer getters e setters
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco_unitario")
    private BigDecimal preco;

}
