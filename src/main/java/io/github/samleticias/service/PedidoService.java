package io.github.samleticias.service;

import io.github.samleticias.domain.entity.Pedido;
import io.github.samleticias.domain.enums.StatusPedido;
import io.github.samleticias.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
    Optional <Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
