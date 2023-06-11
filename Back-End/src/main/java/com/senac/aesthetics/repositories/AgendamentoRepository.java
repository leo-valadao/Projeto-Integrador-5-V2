package com.senac.aesthetics.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Agendamento;
import com.senac.aesthetics.domains.filters.AgendamentoFiltro;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

  @Query("SELECT a FROM Agendamento a " +
      " WHERE NOT EXISTS ( " +
      "   SELECT o FROM OrdemServico o " +
      "   WHERE o.agendamento  = a " +
      ")")
  List<Agendamento> obterAgendamentosSemOrdemServiço();

  @Query("SELECT a FROM Agendamento a ")
  Page<Agendamento> obterPorFiltroComPaginacao(@Param("filtro") AgendamentoFiltro filtro, Pageable pagina);

}
