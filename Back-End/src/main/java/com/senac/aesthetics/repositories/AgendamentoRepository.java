package com.senac.aesthetics.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

  @Query("SELECT a FROM Agendamento a " +
      " WHERE 1 = 1 " +
      "     AND (:#{#filtro} IS NULL) OR " +
      "     ((:#{#filtro.id} IS NULL OR :#{#filtro.id} <= 0) OR a.id = :#{#filtro.id}) AND " +
      "     (:#{#filtro.data} IS NULL OR a.data = :#{#filtro.data}) AND " +
      "     (:#{#filtro.hora} IS NULL OR a.hora = :#{#filtro.hora}) AND " +
      "     (:#{#filtro.status} IS NULL OR a.status = :#{#filtro.status}) AND " +
      "     (:#{#filtro.observacao} IS NULL OR a.observacao = :#{#filtro.observacao})")
  public Page<Agendamento> obterAgendamentosPorFiltro(@Param("filtro") Agendamento filtro, Pageable pagina);
}
