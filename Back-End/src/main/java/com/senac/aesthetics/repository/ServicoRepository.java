package com.senac.aesthetics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domain.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>  {
    
}
