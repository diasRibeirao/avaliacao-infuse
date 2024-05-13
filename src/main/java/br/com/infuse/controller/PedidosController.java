package br.com.infuse.controller;

import br.com.infuse.model.converter.PedidoConverter;
import br.com.infuse.model.dto.PedidoDTO;
import br.com.infuse.model.dto.PedidosCadastrarDTO;
import br.com.infuse.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Pedidos")
@RequestMapping(path = "/v1/pedidos")
public class PedidosController {

    private PedidoService service;

    private PedidoConverter converter;

    public PedidosController(PedidoService service, PedidoConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @Operation(summary = "Consultar os pedidos enviados pelos clientes")
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> consultar(@RequestParam(required = false) String numeroControle,
                                                     @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataCadastro) {
        List<PedidoDTO> list = converter.parse(service.consultar(numeroControle, dataCadastro));

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Cadastrar os pedidos enviados pelos clientes")
    @PostMapping
    public ResponseEntity<String> cadastrar(@Valid @RequestBody PedidosCadastrarDTO cadastrar) {
        service.cadastrar(converter.parsePedidosCadastrarDTO(cadastrar));
        return ResponseEntity.ok("Pedidos recebidos e cadastrados com sucesso!");
    }

}
