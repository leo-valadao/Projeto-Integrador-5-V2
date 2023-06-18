package com.senac.aesthetics.interfaces;

import java.util.List;

import com.senac.aesthetics.domains.Agendamento;

public interface InterfaceResourceObterAgendamentosSemOrdemServico {

  public List<Agendamento> obterAgendamentosSemOrdemServiço() throws Exception;

}
