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
import com.senac.aesthetics.domains.filters.FuncionarioFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/funcionario")
public class FuncionarioResource implements InterfaceGenericaCliente<Funcionario, FuncionarioFiltro> {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<Funcionario, FuncionarioFiltro> funcionarioService;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Funcionario>> obterTodosComPaginacao(
            @RequestParam(required = false, name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(required = false, name = "ordenarPor", defaultValue = "id") String ordernarPor,
            @RequestBody(required = false) FuncionarioFiltro filtro) throws Exception {
        Page<Funcionario> funcionarios = funcionarioService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor, filtro);

        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping
    public ResponseEntity<Funcionario> salvar(@RequestBody @Valid Funcionario funcionario) throws Exception {
        Funcionario funcionarioInserido = funcionarioService.salvar(funcionario);

        return ResponseEntity.created(null).body(funcionarioInserido);
    }

    @PutMapping
    public ResponseEntity<Funcionario> atualizar(@RequestBody @Valid Funcionario funcionario) throws Exception {
        Funcionario funcionarioAtualizado = funcionarioService.atualizar(funcionario);

        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) throws Exception {
        funcionarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
