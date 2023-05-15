package com.senac.aesthetics.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.errors.DataBaseException;

/* 
 * O nome "Generica" é porque a interface é reutilizada em mais de um serviço (Service), sendo genérica para o projeto.
 * O nome "Resource" é porque a interface é utilizada na camada de recurso (Resource) e satisfaz as necessidades dos clientes (Front-End),
 * por causa do princípio da inversão das dependências do SOLID
 */
public interface InterfaceGenericaResource<T> {

    public Page<T> obterTodosComPaginacao(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public T obterPorId(Long idT) throws DataBaseException;

    public T inserir(T T) throws DataBaseException;

    public T atualizar(T T) throws DataBaseException;

    public void excluir(Long idT) throws DataBaseException;

}
