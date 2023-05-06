package com.senac.aesthetics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domains.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>  {
    
}
