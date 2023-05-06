package com.senac.aesthetics.services.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.domains.ContaReceber;
import com.senac.aesthetics.errors.DataBaseException;

public interface IContaReceberService {

    public Page<ContaReceber> obterTodasContasReceber(
    Integer numeroPagina, Integer quantidadePorPagina,
    String ordenarPor)throws DataBaseException;

    public ContaReceber obterContaReceberPorId(Long idContaReceber) throws DataBaseException;

    public ContaReceber inserirContaReceber(ContaReceber contaReceber) throws DataBaseException;

    public ContaReceber atualizarContaReceber(ContaReceber contaReceber) throws DataBaseException;

    public void excluirContaReceber(Long idContaReceber)throws DataBaseException;

}
