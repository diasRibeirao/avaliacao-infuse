package br.com.infuse.service;

import br.com.infuse.model.Pedido;
import br.com.infuse.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testConsultar_PorNumeroControle() {
        String numeroControle = "12345";
        Pedido pedido = new Pedido();
        pedido.setNumeroControle(numeroControle);
        when(repository.findByNumeroControle(numeroControle)).thenReturn(Optional.of(pedido));

        List<Pedido> result = service.consultar(numeroControle, null);

        assertEquals(1, result.size());
        assertEquals(numeroControle, result.get(0).getNumeroControle());
    }

    @Test
    void testConsultar_PorDataCadastro() {
        LocalDate dataCadastro = LocalDate.now();
        LocalDateTime startDateTime = dataCadastro.atStartOfDay();
        LocalDateTime endDateTime = dataCadastro.atTime(23, 59, 59);
        when(repository.findAllByDataCadastroBetween(startDateTime, endDateTime)).thenReturn(Collections.emptyList());

        List<Pedido> result = service.consultar(null, dataCadastro);

        assertTrue(result.isEmpty());
    }

    @Test
    void testConsultar_SemFiltros() {
        List<Pedido> pedidos = Collections.singletonList(new Pedido());
        when(repository.findAll()).thenReturn(pedidos);

        List<Pedido> result = service.consultar(null, null);

        assertEquals(pedidos, result);
    }

    @Test
    void testCadastrar_QuantidadeMenorQue5() {
        Pedido pedido = new Pedido();
        pedido.setValor(BigDecimal.TEN);
        pedido.setQuantidade(5);
        List<Pedido> pedidos = Collections.singletonList(pedido);

        service.cadastrar(pedidos);

        assertEquals(BigDecimal.valueOf(50), pedido.getValorTotal());
        assertNull(pedido.getObservacao());
        verify(repository, times(1)).saveAll(pedidos);
    }

    @Test
    void testCadastrar_QuantidadeMaiorQue5() {
        Pedido pedido = new Pedido();
        pedido.setValor(BigDecimal.ONE);
        pedido.setQuantidade(7);
        List<Pedido> pedidos = Collections.singletonList(pedido);

        service.cadastrar(pedidos);
        assertEquals(BigDecimal.valueOf(6.65), pedido.getValorTotal());
        assertEquals("Aplicado desconto de 5% no valor total do pedido pela quantidade ser maior que 5", pedido.getObservacao());

        verify(repository, times(1)).saveAll(pedidos);
    }

    @Test
    void testCadastrar_QuantidadeAPartirDe10() {
        Pedido pedido = new Pedido();
        pedido.setValor(BigDecimal.TEN);
        pedido.setQuantidade(10);
        List<Pedido> pedidos = Collections.singletonList(pedido);

        service.cadastrar(pedidos);
        assertEquals(BigDecimal.valueOf(90.0), pedido.getValorTotal());
        assertEquals("Aplicado desconto de 10% no valor total do pedido pela quantidade ser a partir de 10", pedido.getObservacao());

        verify(repository, times(1)).saveAll(pedidos);
    }
}