package com.senac.aesthetics.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.senac.aesthetics.domains.Agendamento;

public interface InterfaceClienteObterAgendamentosSemOrdemServico {

  public ResponseEntity<List<Agendamento>> obterAgendamentosSemOrdemServiço() throws Exception;

}
