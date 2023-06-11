package com.senac.aesthetics.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.ContaPagar;
import com.senac.aesthetics.domains.filters.ContaPagarFiltro;

public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

  @Query("SELECT cp FROM ContaPagar cp ")
  Page<ContaPagar> obterPorFiltroComPaginacao(@Param("filtro") ContaPagarFiltro filtro, Pageable pagina);

}
