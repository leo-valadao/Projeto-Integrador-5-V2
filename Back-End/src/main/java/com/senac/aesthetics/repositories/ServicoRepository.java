package com.senac.aesthetics.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Servico;
import com.senac.aesthetics.domains.filters.ServicoFiltro;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

  @Query("SELECT s FROM Servico s ")
  Page<Servico> obterPorFiltroComPaginacao(@Param("filtro") ServicoFiltro filtro, Pageable pagina);

}
