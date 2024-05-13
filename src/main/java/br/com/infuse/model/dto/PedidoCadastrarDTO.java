package br.com.infuse.model.dto;

import br.com.infuse.controller.validation.PedidoCadastrar;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
@PedidoCadastrar
public class PedidoCadastrarDTO {

    @NotNull(message = "O número controle é obrigatório")
    @Size(min = 1, max = 40, message = "O número controle deve possuir entre {min} e {max} caracteres")
    @Schema(example = "123-456-789")
    private String numeroControle;

    @Schema(type = "string", pattern = "dd/MM/yyyy HH:mm:ss", example = "12/05/2024 20:28:11")
    private String dataCadastro;

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 80, message = "O nome deve possuir entre {min} e {max} caracteres")
    @Schema(example = "Memória RAM 8GB 1 DDR4 3200Mhz")
    private String nome;

    @NotNull(message = "O valor é obrigatório")
    @Schema(example = "134.99")
    private BigDecimal valor;

    @Schema(example = "6")
    private Integer quantidade;

    @NotNull(message = "O código do cliente é obrigatório")
    @Schema(example = "1")
    private Long codigoCliente;

}
