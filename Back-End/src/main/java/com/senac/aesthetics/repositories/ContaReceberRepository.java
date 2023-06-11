package com.senac.aesthetics.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.ContaReceber;
import com.senac.aesthetics.domains.filters.ContaReceberFiltro;

public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long> {

  @Query("SELECT cr FROM ContaReceber cr ")
  Page<ContaReceber> obterPorFiltroComPaginacao(@Param("filtro") ContaReceberFiltro filtro, Pageable pagina);

}
