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
import com.senac.aesthetics.interfaces.IGenericaResource;
import com.senac.aesthetics.interfaces.IGenericaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/cliente")
public class ClienteResource implements IGenericaResource<Cliente> {

    // Obejtos:
    @Autowired
    private IGenericaService<Cliente> clienteService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Cliente>> obterTodos(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) {
        Page<Cliente> clientes = clienteService.obterTodos(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(clientes);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Cliente> obterPorId(@RequestParam(name = "id") Long id) {
        Cliente cliente = clienteService.obterPorId(id);

        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody @Valid Cliente cliente) {
        Cliente clienteInserido = clienteService.inserir(cliente);

        return ResponseEntity.created(null).body(clienteInserido);
    }

    @PutMapping
    public ResponseEntity<Cliente> atualizar(@RequestBody @Valid Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizar(cliente);

        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) {
        clienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
