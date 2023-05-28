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
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/servico")
public class ServicoResource implements InterfaceGenericaCliente<Servico> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<Servico> servicoService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Servico>> obterTodosComPaginacao(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) throws Exception {
        Page<Servico> servicos = servicoService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(servicos);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Servico> obterPorId(@RequestParam(name = "id") Long id) throws Exception {
        Servico servico = servicoService.obterPorId(id);

        return ResponseEntity.ok(servico);
    }

    @PostMapping
    public ResponseEntity<Servico> inserir(@RequestBody @Valid Servico servico) throws Exception {
        Servico servicoInserido = servicoService.inserir(servico);

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
