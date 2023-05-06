package com.senac.aesthetics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domain.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>  {
    
}
