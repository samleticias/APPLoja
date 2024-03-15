package io.github.samleticias.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // gera propriedades numa classe objeto e vai disponibilizar essa instancia para construir outro objeto
public class InformacoesPedidoDTO {
    private Integer codigo;
    private String cpf;
    private String dataPedido;
    private String nomeCliente;
    private BigDecimal total;
    private List<InformacaoItemPedidoDTO> items;
}
