package com.senac.aesthetics.resources.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.senac.aesthetics.domains.Cliente;

import jakarta.validation.Valid;

public interface IClienteResource {

    public ResponseEntity<Page<Cliente>> obterTodosClientes(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<Cliente> obterClientePorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<Cliente> inserirCliente(@RequestBody @Valid Cliente cliente);

    public ResponseEntity<Cliente> atualizarCliente(@RequestBody @Valid Cliente cliente);

    public ResponseEntity<Void> excluirCliente(@RequestParam(name = "id") Long id);

}
