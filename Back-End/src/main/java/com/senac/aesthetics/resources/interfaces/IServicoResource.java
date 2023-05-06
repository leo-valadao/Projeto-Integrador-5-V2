package com.senac.aesthetics.resources.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.senac.aesthetics.domains.Servico;

import jakarta.validation.Valid;

public interface IServicoResource {

    public ResponseEntity<Page<Servico>> obterTodosServicos(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<Servico> obterServicoPorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<Servico> inserirServico(@RequestBody @Valid Servico servico);

    public ResponseEntity<Servico> atualizarServico(@RequestBody @Valid Servico servico);

    public ResponseEntity<Void> excluirServico(@RequestParam(name = "id") Long id);

}
