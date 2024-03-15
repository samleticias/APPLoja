package io.github.samleticias.service.impl;

import io.github.samleticias.domain.entity.Cliente;
import io.github.samleticias.domain.entity.ItemPedido;
import io.github.samleticias.domain.entity.Pedido;
import io.github.samleticias.domain.entity.Produto;
import io.github.samleticias.domain.repository.ClientesRepository;
import io.github.samleticias.domain.repository.ItemsPedidoRepository;
import io.github.samleticias.domain.repository.PedidosRepository;
import io.github.samleticias.domain.repository.ProdutosRepository;
import io.github.samleticias.exception.RegraNegocioException;
import io.github.samleticias.rest.dto.ItemPedidoDTO;
import io.github.samleticias.rest.dto.PedidoDTO;
import io.github.samleticias.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // n precisa declarar construtor usando essa notação
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItemsPedidoRepository itemsPedidoRepository;

    @Override
    @Transactional // mesmo apos pedido ser salvo, se houver algum erro, o pedido será desfeito -> faz tudo ou faz nada
    // garante a integridade do pedido e so será salvo se tiver itens do pedido
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));


        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido); // salva itens do pedido na lista de itemsPedido na base de dados
        pedido.setItens(itemsPedido); // setar pedidos da lista itemsPedido
        return pedido;

    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItems(id);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if (items.isEmpty()){ //responsavel por converter items em itensPedido
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return items
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: " + idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;

                } ).collect(Collectors.toList());
    }

}
