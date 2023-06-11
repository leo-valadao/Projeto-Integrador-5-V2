package com.senac.aesthetics.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.OrdemServico;
import com.senac.aesthetics.domains.filters.OrdemServicoFiltro;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

  @Query("SELECT os FROM OrdemServico os ")
  Page<OrdemServico> obterPorFiltroComPaginacao(@Param("filtro") OrdemServicoFiltro filtro, Pageable pagina);

}
