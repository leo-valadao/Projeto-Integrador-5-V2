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

import com.senac.aesthetics.domains.Funcionario;
import com.senac.aesthetics.interfaces.IGenericaResource;
import com.senac.aesthetics.interfaces.IGenericaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/funcionario")
public class FuncionarioResource implements IGenericaResource<Funcionario> {

    // Obejtos:
    @Autowired
    private IGenericaService<Funcionario> funcionarioService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Funcionario>> obterTodos(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) {
        Page<Funcionario> funcionarios = funcionarioService.obterTodos(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Funcionario> obterPorId(@RequestParam(name = "id") Long id) {
        Funcionario funcionario = funcionarioService.obterPorId(id);

        return ResponseEntity.ok(funcionario);
    }

    @PostMapping
    public ResponseEntity<Funcionario> inserir(@RequestBody @Valid Funcionario funcionario) {
        Funcionario funcionarioInserido = funcionarioService.inserir(funcionario);

        return ResponseEntity.created(null).body(funcionarioInserido);
    }

    @PutMapping
    public ResponseEntity<Funcionario> atualizar(@RequestBody @Valid Funcionario funcionario) {
        Funcionario funcionarioAtualizado = funcionarioService.atualizar(funcionario);

        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) {
        funcionarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
