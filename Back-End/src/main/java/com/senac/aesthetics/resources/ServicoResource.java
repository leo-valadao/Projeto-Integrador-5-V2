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
import com.senac.aesthetics.interfaces.IGenericaResource;
import com.senac.aesthetics.interfaces.IGenericaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/servico")
public class ServicoResource implements IGenericaResource<Servico> {

    // Obejtos:
    @Autowired
    private IGenericaService<Servico> servicoService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Servico>> obterTodos(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) {
        Page<Servico> servicos = servicoService.obterTodos(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(servicos);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Servico> obterPorId(@RequestParam(name = "id") Long id) {
        Servico servico = servicoService.obterPorId(id);

        return ResponseEntity.ok(servico);
    }

    @PostMapping
    public ResponseEntity<Servico> inserir(@RequestBody @Valid Servico servico) {
        Servico servicoInserido = servicoService.inserir(servico);

        return ResponseEntity.created(null).body(servicoInserido);
    }

    @PutMapping
    public ResponseEntity<Servico> atualizar(@RequestBody @Valid Servico servico) {
        Servico servicoAtualizado = servicoService.atualizar(servico);

        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) {
        servicoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
