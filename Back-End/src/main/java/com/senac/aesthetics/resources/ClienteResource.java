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

import com.senac.aesthetics.domains.Cliente;
import com.senac.aesthetics.domains.filters.ClienteFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/cliente")
public class ClienteResource implements InterfaceGenericaCliente<Cliente, ClienteFiltro> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<Cliente, ClienteFiltro> clienteService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Cliente>> obterTodosComPaginacao(
            @RequestParam(required = false, name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(required = false, name = "ordenarPor", defaultValue = "id") String ordernarPor,
            @RequestBody(required = false) ClienteFiltro filtro) throws Exception {
        Page<Cliente> clientes = clienteService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor, filtro);

        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody @Valid Cliente cliente) throws Exception {
        Cliente clienteInserido = clienteService.salvar(cliente);

        return ResponseEntity.created(null).body(clienteInserido);
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente) throws Exception {
        Cliente clienteAtualizado = clienteService.atualizar(cliente);

        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) throws Exception {
        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
