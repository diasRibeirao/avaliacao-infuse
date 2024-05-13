package br.com.infuse.service;

import br.com.infuse.model.Pedido;
import br.com.infuse.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> consultar(String numeroControle, LocalDate dataCadastro) {
        if (!StringUtils.isBlank(numeroControle)) {
            Optional<Pedido> pedido = repository.findByNumeroControle(numeroControle);
            return pedido.map(Collections::singletonList).orElse(Collections.emptyList());
        } else if (dataCadastro != null) {
            LocalDateTime dataHora = dataCadastro.atStartOfDay();
            LocalDateTime inicio = dataHora.with(LocalTime.MIN);
            LocalDateTime fim = dataHora.with(LocalTime.MAX);
            return repository.findAllByDataCadastroBetween(inicio, fim);
        } else {
            return repository.findAll();
        }
    }

    @Transactional
    public void cadastrar(List<Pedido> pedidos) {
        pedidos.forEach(this::prepararPedidoParaCadastro);
        repository.saveAll(pedidos);
    }

    private void prepararPedidoParaCadastro(Pedido pedido) {
        if (pedido.getDataCadastro() == null) {
            pedido.setDataCadastro(LocalDateTime.now());
        }

        if (pedido.getQuantidade() == null || pedido.getQuantidade() == 0) {
            pedido.setQuantidade(1);
        }

        BigDecimal valorTotal = pedido.getValor().multiply(BigDecimal.valueOf(pedido.getQuantidade()));

        if (pedido.getQuantidade() >= 10) {
            pedido.setValorTotal(valorTotal.multiply(BigDecimal.valueOf(0.9)));
            pedido.setObservacao("Aplicado desconto de 10% no valor total do pedido pela quantidade ser a partir de 10");
        } else if (pedido.getQuantidade() > 5) {
            pedido.setValorTotal(valorTotal.multiply(BigDecimal.valueOf(0.95)));
            pedido.setObservacao("Aplicado desconto de 5% no valor total do pedido pela quantidade ser maior que 5");
        } else {
            pedido.setValorTotal(valorTotal);
        }
    }


}
