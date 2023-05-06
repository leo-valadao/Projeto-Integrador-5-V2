package com.senac.aesthetics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>  {
    
}
