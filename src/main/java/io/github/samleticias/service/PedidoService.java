package io.github.samleticias.service;

import io.github.samleticias.domain.entity.Pedido;
import io.github.samleticias.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
}
