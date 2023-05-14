package com.senac.aesthetics.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senac.aesthetics.domains.ContaPagar;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/conta-pagar")
public class ContaPagarResource implements InterfaceGenericaCliente<ContaPagar> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<ContaPagar> contaPagarService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<ContaPagar>> obterTodosComPaginacao(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) {
        Page<ContaPagar> contaPagars = contaPagarService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(contaPagars);
    }

    @GetMapping(params = "id")
    public ResponseEntity<ContaPagar> obterPorId(@RequestParam(name = "id") Long id) {
        ContaPagar contaPagar = contaPagarService.obterPorId(id);

        return ResponseEntity.ok(contaPagar);
    }

    @PostMapping
    public ResponseEntity<ContaPagar> inserir(@RequestBody @Valid ContaPagar contaPagar) {
        ContaPagar contaPagarInserido = contaPagarService.inserir(contaPagar);

        return ResponseEntity.created(null).body(contaPagarInserido);
    }

    @PutMapping
    public ResponseEntity<ContaPagar> atualizar(@RequestBody @Valid ContaPagar contaPagar) {
        ContaPagar contaPagarAtualizado = contaPagarService.atualizar(contaPagar);

        return ResponseEntity.ok(contaPagarAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) {
        contaPagarService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
