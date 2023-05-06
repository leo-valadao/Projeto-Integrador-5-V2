package com.senac.aesthetics.resources.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.senac.aesthetics.domains.Fornecedor;

import jakarta.validation.Valid;

public interface IFornecedorResource {

    public ResponseEntity<Page<Fornecedor>> obterTodosFornecedores(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<Fornecedor> obterFornecedorPorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<Fornecedor> inserirFornecedor(@RequestBody @Valid Fornecedor fornecedor);

    public ResponseEntity<Fornecedor> atualizarFornecedor(@RequestBody @Valid Fornecedor fornecedor);

    public ResponseEntity<Void> excluirFornecedor(@RequestParam(name = "id") Long id);

}
