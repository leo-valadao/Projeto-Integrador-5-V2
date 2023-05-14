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

import com.senac.aesthetics.domains.OrdemServico;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/ordem-servico")
public class OrdemServicoResource implements InterfaceGenericaCliente<OrdemServico> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<OrdemServico> ordemServicoService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<OrdemServico>> obterTodosComPaginacao(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) {
        Page<OrdemServico> ordemServicos = ordemServicoService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(ordemServicos);
    }

    @GetMapping(params = "id")
    public ResponseEntity<OrdemServico> obterPorId(@RequestParam(name = "id") Long id) {
        OrdemServico ordemServico = ordemServicoService.obterPorId(id);

        return ResponseEntity.ok(ordemServico);
    }

    @PostMapping
    public ResponseEntity<OrdemServico> inserir(@RequestBody @Valid OrdemServico ordemServico) {
        OrdemServico ordemServicoInserido = ordemServicoService.inserir(ordemServico);

        return ResponseEntity.created(null).body(ordemServicoInserido);
    }

    @PutMapping
    public ResponseEntity<OrdemServico> atualizar(@RequestBody @Valid OrdemServico ordemServico) {
        OrdemServico ordemServicoAtualizado = ordemServicoService.atualizar(ordemServico);

        return ResponseEntity.ok(ordemServicoAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) {
        ordemServicoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
