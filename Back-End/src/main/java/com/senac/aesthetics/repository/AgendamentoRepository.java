package com.senac.aesthetics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domain.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>  {
    
}
