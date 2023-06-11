package com.senac.aesthetics.interfaces;

import org.springframework.data.domain.Page;

/* 
 * O nome "Generica" é porque a interface é reutilizada em mais de um serviço (Service), sendo genérica para o projeto.
 * O nome "Resource" é porque a interface é utilizada na camada de recurso (Resource) e satisfaz as necessidades dos clientes (Front-End),
 * por causa do princípio da inversão das dependências do SOLID
 */
public interface InterfaceGenericaResource<T, TFiltro> {

    public Page<T> obterTodosComPaginacao(
        Integer numeroPagina,
        Integer quantidadePorPagina, 
        String ordenarPor, 
        TFiltro filtro) throws Exception;

    public T salvar(T T) throws Exception;

    public T atualizar(T T) throws Exception;

    public void excluir(Long idT) throws Exception;

}
