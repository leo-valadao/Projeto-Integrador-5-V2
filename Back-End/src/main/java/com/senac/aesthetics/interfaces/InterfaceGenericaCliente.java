package com.senac.aesthetics.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

/* 
 * O nome "Generica" é porque a interface é reutilizada em mais de recurso (Resource), sendo genérica para o projeto.
 * O nome "Resource" é porque a interface é utilizada na camada de serviço (Service) e satisfaz as necessidades das controladoras (Resource),
 * por causa do princípio da inversão das dependências do SOLID
 */
public interface InterfaceGenericaCliente<T, TFiltro> {

    public ResponseEntity<Page<T>> obterTodosComPaginacao(
            @RequestParam(required = false, name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(required = false, name = "ordenarPor", defaultValue = "id") String ordernarPor,
            @RequestBody(required = false) TFiltro filtro) throws Exception;

    public ResponseEntity<T> salvar(@RequestBody @Valid T T) throws Exception;

    public ResponseEntity<T> atualizar(@RequestBody @Valid T T) throws Exception;

    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) throws Exception;

}
