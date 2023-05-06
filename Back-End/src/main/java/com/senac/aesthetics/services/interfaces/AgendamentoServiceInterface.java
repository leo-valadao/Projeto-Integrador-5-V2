package com.senac.aesthetics.services.interfaces;

import org.springframework.data.domain.Page;

import com.senac.aesthetics.domains.Agendamento;
import com.senac.aesthetics.errors.DataBaseException;

public interface AgendamentoServiceInterface {

    public Page<Agendamento> obterTodosAgendamentos(
            Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws DataBaseException;

    public Agendamento obterAgendamentoPorId(Long idAgendamento) throws DataBaseException;

    public Agendamento inserirAgendamento(Agendamento agendamento) throws DataBaseException;

    public Agendamento atualizarAgendamento(Agendamento agendamento) throws DataBaseException;

    public void excluirAgendamento(Long idAgendamento) throws DataBaseException;

}
