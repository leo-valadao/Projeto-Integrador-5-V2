package com.senac.aesthetics.services.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.domains.ContaPagar;
import com.senac.aesthetics.errors.DataBaseException;

public interface IContaPagarService {

    public Page<ContaPagar> obterTodasContasPagar(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public ContaPagar obterContaPagarPorId(Long idContaPagar) throws DataBaseException;

    public ContaPagar inserirContaPagar(ContaPagar contaPagar) throws DataBaseException;

    public ContaPagar atualizarContaPagar(ContaPagar contaPagar) throws DataBaseException;

    public void excluirContaPagar(Long idContaPagar) throws DataBaseException;

}
