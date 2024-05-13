package br.com.infuse.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "PEDIDOS")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PEDIDO")
    private Long codigoPedido;

    @Column(name = "NUMERO_CONTROLE")
    private String numeroControle;

    @Column(name = "DATA_CADASTRO")
    private LocalDateTime dataCadastro;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "VALOR", precision = 38, scale = 2)
    private BigDecimal valor;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "CODIGO_CLIENTE")
    private Cliente cliente;

    @Column(name = "VALOR_TOTAL", precision = 38, scale = 2)
    private BigDecimal  valorTotal;

    @Column(name = "OBSERVACAO")
    private String observacao;

}
