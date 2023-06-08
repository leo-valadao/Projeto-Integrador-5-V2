package com.senac.aesthetics.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.senac.aesthetics.domains.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

  @Query("SELECT a FROM Agendamento a " +
      " WHERE NOT EXISTS ( " +
      "   SELECT o FROM OrdemServico o " +
      "   WHERE o.agendamento  = a " +
      ")")
  List<Agendamento> obterAgendamentosSemOrdemServiço();

}
