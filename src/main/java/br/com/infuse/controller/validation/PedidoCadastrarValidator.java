package br.com.infuse.controller.validation;

import br.com.infuse.controller.exceptions.FieldMessage;
import br.com.infuse.model.Cliente;
import br.com.infuse.model.Pedido;
import br.com.infuse.model.dto.PedidoCadastrarDTO;
import br.com.infuse.repository.ClienteRepository;
import br.com.infuse.repository.PedidoRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoCadastrarValidator implements ConstraintValidator<PedidoCadastrar, PedidoCadastrarDTO> {

    private PedidoRepository pedidoRepository;

    private ClienteRepository clienteRepository;

    public PedidoCadastrarValidator(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }


    @Override
    public void initialize(PedidoCadastrar ann) {
        // TODO document why this method is empty
    }

    @Override
    public boolean isValid(PedidoCadastrarDTO pedidoCadastrarDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Optional<Cliente> cliente = clienteRepository.findById(pedidoCadastrarDTO.getCodigoCliente());
        if (cliente.isEmpty()) {
            list.add(new FieldMessage("codigoCliente", "Código do cliente não cadastrado"));
        }

        Optional<Pedido> pedido = pedidoRepository.findByNumeroControle(pedidoCadastrarDTO.getNumeroControle());
        if (pedido.isPresent()) {
            list.add(new FieldMessage("numeroControle", "Número de controle já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
