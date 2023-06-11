package com.senac.aesthetics.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Fornecedor;
import com.senac.aesthetics.domains.filters.FornecedorFiltro;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

  @Query("SELECT f FROM Fornecedor f WHERE f.pessoa.cpfOuCnpj = :cpfOuCnpj")
  Optional<Fornecedor> obterPorCpfOuCnpj(@Param("cpfOuCnpj") String cpfOuCnpj);

  @Query("SELECT f FROM Fornecedor f ")
  Page<Fornecedor> obterPorFiltroComPaginacao(@Param("filtro") FornecedorFiltro filtro, Pageable pagina);

}
