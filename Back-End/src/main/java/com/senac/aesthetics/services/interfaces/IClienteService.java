package com.senac.aesthetics.services.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.domains.Cliente;
import com.senac.aesthetics.errors.DataBaseException;

public interface IClienteService {

    public Page<Cliente> obterTodosClientes(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public Cliente obterClientePorId(Long idCliente) throws DataBaseException;

    public Cliente inserirCliente(Cliente cliente) throws DataBaseException;

    public Cliente atualizarCliente(Cliente cliente) throws DataBaseException;

    public void excluirCliente(Long idCliente) throws DataBaseException;

}
