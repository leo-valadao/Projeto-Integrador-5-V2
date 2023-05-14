package com.senac.aesthetics.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.errors.DataBaseException;

public interface InterfaceGenericaResource<T> {

    public Page<T> obterTodosComPaginacao(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public T obterPorId(Long idT) throws DataBaseException;

    public T inserir(T T) throws DataBaseException;

    public T atualizar(T T) throws DataBaseException;

    public void excluir(Long idT) throws DataBaseException;

}
