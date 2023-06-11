package com.senac.aesthetics.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Funcionario;
import com.senac.aesthetics.domains.filters.FuncionarioFiltro;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

  @Query("SELECT f FROM Funcionario f WHERE f.pessoa.cpfOuCnpj = :cpfOuCnpj")
  Optional<Funcionario> obterPorCpfOuCnpj(@Param("cpfOuCnpj") String cpfOuCnpj);

  @Query("SELECT f FROM Funcionario f ")
  Page<Funcionario> obterPorFiltroComPaginacao(@Param("filtro") FuncionarioFiltro filtro, Pageable pagina);

}
