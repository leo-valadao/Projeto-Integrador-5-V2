package com.senac.aesthetics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>  {
    
}
