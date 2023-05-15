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
public interface InterfaceGenericaCliente<T> {

    public ResponseEntity<Page<T>> obterTodosComPaginacao(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<T> obterPorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<T> inserir(@RequestBody @Valid T T);

    public ResponseEntity<T> atualizar(@RequestBody @Valid T T);

    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id);

}
