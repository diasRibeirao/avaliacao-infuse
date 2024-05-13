package br.com.infuse.model.converter;

import br.com.infuse.model.Cliente;
import br.com.infuse.model.Pedido;
import br.com.infuse.model.dto.PedidoCadastrarDTO;
import br.com.infuse.model.dto.PedidoDTO;
import br.com.infuse.model.dto.PedidosCadastrarDTO;
import br.com.infuse.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoConverter {

    public PedidoDTO parse(Pedido origin) {
        if (origin == null)
            return null;

        return PedidoDTO.builder()
                .codigoPedido(origin.getCodigoPedido())
                .numeroControle(origin.getNumeroControle())
                .dataCadastro(origin.getDataCadastro())
                .nome(origin.getNome())
                .valor(origin.getValor())
                .quantidade(origin.getQuantidade())
                .codigoCliente(origin.getCliente().getCodigoCliente())
                .valorTotal(origin.getValorTotal())
                .observacao(origin.getObservacao())
                .build();
    }

    public List<PedidoDTO> parse(List<Pedido> origin) {
        if (origin == null)
            return Collections.emptyList();

        return origin.stream().map(this::parse).collect(Collectors.toList());
    }

    public Pedido parsePedidosCadastrarDTO(PedidoCadastrarDTO origin) {
        if (origin == null)
            return null;

        return Pedido.builder()
                .numeroControle(origin.getNumeroControle())
                .dataCadastro(Utils.stringToLocalDateTime(origin.getDataCadastro()))
                .nome(origin.getNome())
                .valor(origin.getValor())
                .quantidade(origin.getQuantidade())
                .cliente(Cliente.builder().codigoCliente(origin.getCodigoCliente()).build())
                .build();
    }

    public List<Pedido> parsePedidosCadastrarDTO(PedidosCadastrarDTO origin) {
        return origin.getPedidos().stream()
                .map(obj -> parsePedidosCadastrarDTO(obj))
                .collect(Collectors.toList());
    }


}

