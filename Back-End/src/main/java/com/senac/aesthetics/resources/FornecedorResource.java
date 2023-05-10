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

import com.senac.aesthetics.domains.Fornecedor;
import com.senac.aesthetics.interfaces.IGenericaResource;
import com.senac.aesthetics.interfaces.IGenericaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/fornecedor", produces = "application/json")
public class FornecedorResource implements IGenericaResource<Fornecedor> {

    // Obejtos:
    @Autowired
    private IGenericaService<Fornecedor> fornecedorService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Fornecedor>> obterTodos(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) {
        Page<Fornecedor> fornecedors = fornecedorService.obterTodos(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(fornecedors);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Fornecedor> obterPorId(@RequestParam(name = "id") Long id) {
        Fornecedor fornecedor = fornecedorService.obterPorId(id);

        return ResponseEntity.ok(fornecedor);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> inserir(@RequestBody @Valid Fornecedor fornecedor) {
        Fornecedor fornecedorInserido = fornecedorService.inserir(fornecedor);

        return ResponseEntity.created(null).body(fornecedorInserido);
    }

    @PutMapping
    public ResponseEntity<Fornecedor> atualizar(@RequestBody @Valid Fornecedor fornecedor) {
        Fornecedor fornecedorAtualizado = fornecedorService.atualizar(fornecedor);

        return ResponseEntity.ok(fornecedorAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) {
        fornecedorService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
