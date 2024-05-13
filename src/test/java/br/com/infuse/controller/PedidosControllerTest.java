package br.com.infuse.controller;

import br.com.infuse.model.dto.PedidosCadastrarDTO;
import br.com.infuse.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PedidosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Mock
    private PedidosCadastrarDTO mockPedidosCadastrarDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void consultarPedidos() throws Exception {
        when(pedidoService.consultar(null, null))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/pedidos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void cadastrarPedido() throws Exception {
        doNothing().when(pedidoService).cadastrar(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/pedidos")
                        .content("{\n" +
                                "  \"pedidos\": [\n" +
                                "    {\n" +
                                "      \"numeroControle\": \"000-000-700\",\n" +
                                "      \"dataCadastro\": \"12/05/2024 20:28:11\",\n" +
                                "      \"nome\": \"Memória RAM 4GB 1 DDR4 3200Mhz\",\n" +
                                "      \"valor\": 134.99,\n" +
                                "      \"quantidade\": 6,\n" +
                                "      \"codigoCliente\": 1\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Pedidos recebidos e cadastrados com sucesso!"));
    }

}