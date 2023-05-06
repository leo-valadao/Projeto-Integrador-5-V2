package com.senac.aesthetics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domains.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>  {
    
}
