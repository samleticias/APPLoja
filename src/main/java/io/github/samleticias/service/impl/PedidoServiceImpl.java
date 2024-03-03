package io.github.samleticias.service.impl;

import io.github.samleticias.domain.repository.PedidosRepository;
import io.github.samleticias.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository repository;

    public PedidoServiceImpl(PedidosRepository repository) {
        this.repository = repository;
    }
}
