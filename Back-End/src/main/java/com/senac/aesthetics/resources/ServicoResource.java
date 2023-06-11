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

import com.senac.aesthetics.domains.Servico;
import com.senac.aesthetics.domains.filters.ServicoFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/servico")
public class ServicoResource implements InterfaceGenericaCliente<Servico, ServicoFiltro> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<Servico, ServicoFiltro> servicoService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Servico>> obterTodosComPaginacao(
            @RequestParam(required = false, name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(required = false, name = "ordenarPor", defaultValue = "id") String ordernarPor,
            @RequestBody(required = false) ServicoFiltro filtro) throws Exception {
        Page<Servico> servicos = servicoService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor, filtro);

        return ResponseEntity.ok(servicos);
    }

    @PostMapping
    public ResponseEntity<Servico> salvar(@RequestBody @Valid Servico servico) throws Exception {
        Servico servicoInserido = servicoService.salvar(servico);

        return ResponseEntity.created(null).body(servicoInserido);
    }

    @PutMapping
    public ResponseEntity<Servico> atualizar(@RequestBody @Valid Servico servico) throws Exception {
        Servico servicoAtualizado = servicoService.atualizar(servico);

        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) throws Exception {
        servicoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
