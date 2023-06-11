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

import com.senac.aesthetics.domains.ContaReceber;
import com.senac.aesthetics.domains.filters.ContaReceberFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/conta-receber")
public class ContaReceberResource implements InterfaceGenericaCliente<ContaReceber, ContaReceberFiltro> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<ContaReceber, ContaReceberFiltro> contaReceberService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<ContaReceber>> obterTodosComPaginacao(
            @RequestParam(required = false, name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(required = false, name = "ordenarPor", defaultValue = "id") String ordernarPor,
            @RequestBody(required = false) ContaReceberFiltro filtro) throws Exception {
        Page<ContaReceber> contaRecebers = contaReceberService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor, filtro);

        return ResponseEntity.ok(contaRecebers);
    }

    @PostMapping
    public ResponseEntity<ContaReceber> salvar(@RequestBody @Valid ContaReceber contaReceber) throws Exception {
        ContaReceber contaReceberInserido = contaReceberService.salvar(contaReceber);

        return ResponseEntity.created(null).body(contaReceberInserido);
    }

    @PutMapping
    public ResponseEntity<ContaReceber> atualizar(@RequestBody @Valid ContaReceber contaReceber) throws Exception {
        ContaReceber contaReceberAtualizado = contaReceberService.atualizar(contaReceber);

        return ResponseEntity.ok(contaReceberAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) throws Exception {
        contaReceberService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
