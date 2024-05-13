package br.com.infuse.model.dto;

import br.com.infuse.controller.validation.PedidosCadastrar;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@PedidosCadastrar
public class PedidosCadastrarDTO {

    @NotEmpty(message = "Ao menos um pedido é obrigatório")
    @Size(max = 10, message = "O número máximo de pedidos é 10")
    private @Valid List<PedidoCadastrarDTO> pedidos;

}
