package com.senac.aesthetics.resources.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.senac.aesthetics.domains.ContaPagar;

import jakarta.validation.Valid;

public interface IContaPagarResource {

    public ResponseEntity<Page<ContaPagar>> obterTodosContasPagar(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<ContaPagar> obterContaPagarPorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<ContaPagar> inserirContaPagar(@RequestBody @Valid ContaPagar contaPagar);

    public ResponseEntity<ContaPagar> atualizarContaPagar(@RequestBody @Valid ContaPagar contaPagar);

    public ResponseEntity<Void> excluirContaPagar(@RequestParam(name = "id") Long id);

}
