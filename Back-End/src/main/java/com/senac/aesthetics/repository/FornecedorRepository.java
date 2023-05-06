package com.senac.aesthetics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senac.aesthetics.domain.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>  {
    
}
