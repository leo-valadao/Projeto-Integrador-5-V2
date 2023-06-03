package com.senac.aesthetics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domains.abstracts.Pessoa;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

  Optional<Pessoa> findByCpfOuCnpj(String cpfOuCnpj) throws Exception;

}
