package com.senac.aesthetics.services.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.domains.Fornecedor;
import com.senac.aesthetics.errors.DataBaseException;

public interface IFornecedorService {

    public Page<Fornecedor> obterTodosFornecedores(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public Fornecedor obterFornecedorPorId(Long idFornecedor) throws DataBaseException;

    public Fornecedor inserirFornecedor(Fornecedor fornecedor) throws DataBaseException;

    public Fornecedor atualizarFornecedor(Fornecedor fornecedor) throws DataBaseException;

    public void excluirFornecedor(Long idFornecedor) throws DataBaseException;

}
