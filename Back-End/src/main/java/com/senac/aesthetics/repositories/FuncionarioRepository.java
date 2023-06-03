package com.senac.aesthetics.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

  @Query("SELECT f FROM Funcionario f WHERE f.pessoa.cpfOuCnpj = :cpfOuCnpj")
  Optional<Funcionario> obterPorCpfOuCnpj(@Param("cpfOuCnpj") String cpfOuCnpj);

}
