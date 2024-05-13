package br.com.infuse.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PedidoDTO(
        Long codigoPedido,
        String numeroControle,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        @Schema(type = "string", pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataCadastro,
        String nome,
        BigDecimal valor,
        Integer quantidade,
        Long codigoCliente,
        BigDecimal  valorTotal,
        String observacao
) {}
