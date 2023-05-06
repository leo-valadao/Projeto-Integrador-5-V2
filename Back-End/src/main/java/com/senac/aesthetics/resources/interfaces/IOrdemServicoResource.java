package com.senac.aesthetics.resources.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.senac.aesthetics.domains.OrdemServico;

import jakarta.validation.Valid;

public interface IOrdemServicoResource {

    public ResponseEntity<Page<OrdemServico>> obterTodosOrdensServico(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<OrdemServico> obterOrdemServicoPorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<OrdemServico> inserirOrdemServico(@RequestBody @Valid OrdemServico ordemServico);

    public ResponseEntity<OrdemServico> atualizarOrdemServico(@RequestBody @Valid OrdemServico ordemServico);

    public ResponseEntity<Void> excluirOrdemServico(@RequestParam(name = "id") Long id);

}
