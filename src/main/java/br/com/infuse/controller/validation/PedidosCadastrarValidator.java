package br.com.infuse.controller.validation;

import br.com.infuse.controller.exceptions.FieldMessage;
import br.com.infuse.model.dto.PedidosCadastrarDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PedidosCadastrarValidator implements ConstraintValidator<PedidosCadastrar, PedidosCadastrarDTO> {

    @Override
    public void initialize(PedidosCadastrar ann) {
        // TODO document why this method is empty
    }

    @Override
    public boolean isValid(PedidosCadastrarDTO pedidosCadastrarDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Set<String> numerosControle = new HashSet<>();
        for (int i = 0; i < pedidosCadastrarDTO.getPedidos().size(); i++) {
            String numeroControle = pedidosCadastrarDTO.getPedidos().get(i).getNumeroControle();
            if (!numerosControle.add(numeroControle)) {
                list.add(new FieldMessage(
                        "pedidos[" + i + "].numeroControle",
                        "Número de controle deve ser único para cada pedido na lista")
                );
            }
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
