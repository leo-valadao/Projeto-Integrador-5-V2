package com.senac.aesthetics.services.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.domains.Funcionario;
import com.senac.aesthetics.errors.DataBaseException;

public interface IFuncionarioService {

    public Page<Funcionario> obterTodosFuncionarios(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public Funcionario obterFuncionarioPorId(Long idFuncionario) throws DataBaseException;

    public Funcionario inserirFuncionario(Funcionario funcionario) throws DataBaseException;

    public Funcionario atualizarFuncionario(Funcionario funcionario) throws DataBaseException;

    public void excluirFuncionario(Long idFuncionario) throws DataBaseException;

}
