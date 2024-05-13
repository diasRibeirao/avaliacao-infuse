package br.com.infuse.model;

import br.com.infuse.model.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "CLIENTES")
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_CLIENTE")
    private Long codigoCliente;

    @Column(name = "TIPO")
    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

    @Column(name = "CPF_OU_CNPJ", unique = true)
    private String cpfOuCnpj;

    @Column(name = "NOME")
    private String nome;

}
