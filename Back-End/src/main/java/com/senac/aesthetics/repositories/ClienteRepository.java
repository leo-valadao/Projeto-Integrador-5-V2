package com.senac.aesthetics.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.senac.aesthetics.domains.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  @Query("SELECT c FROM Cliente c WHERE c.pessoa.cpfOuCnpj = :cpfOuCnpj")
  Optional<Cliente> obterPorCpfOuCnpj(@Param("cpfOuCnpj") String cpfOuCnpj);

}
