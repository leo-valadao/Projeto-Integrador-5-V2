package com.senac.aesthetics.interfaces;

import java.util.List;

import com.senac.aesthetics.domains.Agendamento;

public interface InterfaceServiceObterAgendamentosSemOrdemServico {

  public List<Agendamento> obterAgendamentosSemOrdemServiço() throws Exception;

}
