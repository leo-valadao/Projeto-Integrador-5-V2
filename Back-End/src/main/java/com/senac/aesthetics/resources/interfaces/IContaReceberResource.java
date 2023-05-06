package com.senac.aesthetics.resources.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.senac.aesthetics.domains.ContaReceber;

import jakarta.validation.Valid;

public interface IContaReceberResource {

    public ResponseEntity<Page<ContaReceber>> obterTodosContasReceber(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<ContaReceber> obterContaReceberPorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<ContaReceber> inserirContaReceber(@RequestBody @Valid ContaReceber contaReceber);

    public ResponseEntity<ContaReceber> atualizarContaReceber(@RequestBody @Valid ContaReceber contaReceber);

    public ResponseEntity<Void> excluirContaReceber(@RequestParam(name = "id") Long id);

}
