package com.senac.aesthetics.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.errors.ErroGenerico;

/* 
 * O nome "Generica" é porque a interface é reutilizada em mais de um serviço (Service), sendo genérica para o projeto.
 * O nome "Resource" é porque a interface é utilizada na camada de recurso (Resource) e satisfaz as necessidades dos clientes (Front-End),
 * por causa do princípio da inversão das dependências do SOLID
 */
public interface InterfaceGenericaResource<T> {

    public Page<T> obterTodosComPaginacao(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws ErroGenerico;

    public T obterPorId(Long idT) throws ErroGenerico;

    public T inserir(T T) throws ErroGenerico;

    public T atualizar(T T) throws ErroGenerico;

    public void excluir(Long idT) throws ErroGenerico;

}
