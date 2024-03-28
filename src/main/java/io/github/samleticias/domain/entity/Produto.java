package io.github.samleticias.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "Campo Descrição é obrigatório.")
    private String descricao;

    // NotEmpty para string: pode ser tanto nula qaunto vazia
    // NotNull: garantia de que o preço não é nulo
    // coloacr @Valid em métodos de salvar e de atualizar em Cliente e em Produto p continuar validando

    @Column(name = "preco_unitario")
    @NotNull(message = "Campo Preço é obrigatório.")
    private BigDecimal preco;

}
