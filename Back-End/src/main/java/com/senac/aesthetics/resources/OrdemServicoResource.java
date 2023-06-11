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
import com.senac.aesthetics.domains.filters.OrdemServicoFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/ordem-servico")
public class OrdemServicoResource implements InterfaceGenericaCliente<OrdemServico, OrdemServicoFiltro> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<OrdemServico, OrdemServicoFiltro> ordemServicoService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<OrdemServico>> obterTodosComPaginacao(
            @RequestParam(required = false, name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(required = false, name = "ordenarPor", defaultValue = "id") String ordernarPor,
            @RequestBody(required = false) OrdemServicoFiltro filtro) throws Exception {
        Page<OrdemServico> ordemServicos = ordemServicoService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor, filtro);

        return ResponseEntity.ok(ordemServicos);
    }

    @PostMapping
    public ResponseEntity<OrdemServico> salvar(@RequestBody @Valid OrdemServico ordemServico) throws Exception {
        OrdemServico ordemServicoInserido = ordemServicoService.salvar(ordemServico);

        return ResponseEntity.created(null).body(ordemServicoInserido);
    }

    @PutMapping
    public ResponseEntity<OrdemServico> atualizar(@RequestBody @Valid OrdemServico ordemServico) throws Exception {
        OrdemServico ordemServicoAtualizado = ordemServicoService.atualizar(ordemServico);

        return ResponseEntity.ok(ordemServicoAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) throws Exception {
        ordemServicoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
