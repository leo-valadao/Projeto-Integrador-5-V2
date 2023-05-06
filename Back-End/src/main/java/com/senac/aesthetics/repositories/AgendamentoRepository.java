package com.senac.aesthetics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domains.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>  {
    
}
