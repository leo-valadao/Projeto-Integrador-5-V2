package com.senac.aesthetics.services.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.domains.Servico;
import com.senac.aesthetics.errors.DataBaseException;

public interface IServicoService {

    public Page<Servico> obterTodosServicos(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public Servico obterServicoPorId(Long idServico) throws DataBaseException;

    public Servico inserirServico(Servico servico) throws DataBaseException;

    public Servico atualizarServico(Servico servico) throws DataBaseException;

    public void excluirServico(Long idServico) throws DataBaseException;

}
