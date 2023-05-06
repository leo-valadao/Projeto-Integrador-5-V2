package com.senac.aesthetics.resources.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.senac.aesthetics.domains.Funcionario;

import jakarta.validation.Valid;

public interface IFuncionarioResource {

    public ResponseEntity<Page<Funcionario>> obterTodosFuncionarios(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<Funcionario> obterFuncionarioPorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<Funcionario> inserirFuncionario(@RequestBody @Valid Funcionario funcionario);

    public ResponseEntity<Funcionario> atualizarFuncionario(@RequestBody @Valid Funcionario funcionario);

    public ResponseEntity<Void> excluirFuncionario(@RequestParam(name = "id") Long id);

}
