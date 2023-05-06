package com.senac.aesthetics.services.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.domains.OrdemServico;
import com.senac.aesthetics.errors.DataBaseException;

public interface OrdemServicoServiceInterface {

    public Page<OrdemServico> obterTodosOrdensServico(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public OrdemServico obterOrdemServicoPorId(Long idOrdemServico) throws DataBaseException;

    public OrdemServico inserirOrdemServico(OrdemServico ordemServico) throws DataBaseException;

    public OrdemServico atualizarOrdemServico(OrdemServico ordemServico) throws DataBaseException;

    public void excluirOrdemServico(Long idOrdemServico) throws DataBaseException;

}
