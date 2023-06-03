package com.senac.aesthetics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domains.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
