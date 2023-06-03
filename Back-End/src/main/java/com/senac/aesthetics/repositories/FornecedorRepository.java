package com.senac.aesthetics.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

  @Query("SELECT f FROM Fornecedor f WHERE f.pessoa.cpfOuCnpj = :cpfOuCnpj")
  Optional<Fornecedor> obterPorCpfOuCnpj(@Param("cpfOuCnpj") String cpfOuCnpj);

}
