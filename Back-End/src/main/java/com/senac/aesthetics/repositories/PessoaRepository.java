package com.senac.aesthetics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Pessoa;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  @Query("SELECT p FROM Pessoa p WHERE p.cpfOuCnpj = :cpfOuCnpj")
  Optional<Pessoa> findByCpfOuCnpj(@Param("cpfOuCnpj") String cpfOuCnpj);

}
