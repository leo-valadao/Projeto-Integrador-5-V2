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

import com.senac.aesthetics.domains.ContaReceber;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/conta-receber")
public class ContaReceberResource implements InterfaceGenericaCliente<ContaReceber> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<ContaReceber> contaReceberService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<ContaReceber>> obterTodosComPaginacao(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) {
        Page<ContaReceber> contaRecebers = contaReceberService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(contaRecebers);
    }

    @GetMapping(params = "id")
    public ResponseEntity<ContaReceber> obterPorId(@RequestParam(name = "id") Long id) {
        ContaReceber contaReceber = contaReceberService.obterPorId(id);

        return ResponseEntity.ok(contaReceber);
    }

    @PostMapping
    public ResponseEntity<ContaReceber> inserir(@RequestBody @Valid ContaReceber contaReceber) {
        ContaReceber contaReceberInserido = contaReceberService.inserir(contaReceber);

        return ResponseEntity.created(null).body(contaReceberInserido);
    }

    @PutMapping
    public ResponseEntity<ContaReceber> atualizar(@RequestBody @Valid ContaReceber contaReceber) {
        ContaReceber contaReceberAtualizado = contaReceberService.atualizar(contaReceber);

        return ResponseEntity.ok(contaReceberAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) {
        contaReceberService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
