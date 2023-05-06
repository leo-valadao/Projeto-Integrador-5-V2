package com.senac.aesthetics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domains.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>  {
    
}
