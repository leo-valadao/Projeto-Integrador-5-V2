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

import com.senac.aesthetics.domains.ContaPagar;
import com.senac.aesthetics.domains.filters.ContaPagarFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/conta-pagar")
public class ContaPagarResource implements InterfaceGenericaCliente<ContaPagar, ContaPagarFiltro> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<ContaPagar, ContaPagarFiltro> contaPagarService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<ContaPagar>> obterTodosComPaginacao(
            @RequestParam(required = false, name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(required = false, name = "ordenarPor", defaultValue = "id") String ordernarPor,
            @RequestBody(required = false) ContaPagarFiltro filtro) throws Exception {
        Page<ContaPagar> contaPagars = contaPagarService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor, filtro);

        return ResponseEntity.ok(contaPagars);
    }

    @PostMapping
    public ResponseEntity<ContaPagar> salvar(@RequestBody @Valid ContaPagar contaPagar) throws Exception {
        ContaPagar contaPagarInserido = contaPagarService.salvar(contaPagar);

        return ResponseEntity.created(null).body(contaPagarInserido);
    }

    @PutMapping
    public ResponseEntity<ContaPagar> atualizar(@RequestBody @Valid ContaPagar contaPagar) throws Exception {
        ContaPagar contaPagarAtualizado = contaPagarService.atualizar(contaPagar);

        return ResponseEntity.ok(contaPagarAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) throws Exception {
        contaPagarService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
