package io.github.samleticias.rest.controller;

import io.github.samleticias.domain.entity.Pedido;
import io.github.samleticias.rest.dto.PedidoDTO;
import io.github.samleticias.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        // lógica de salvar, validar, deletar: método dentro do service -> regras de negocio
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}
