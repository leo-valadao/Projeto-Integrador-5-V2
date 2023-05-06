package com.senac.aesthetics.domain;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor

// Java Persistence API:
@MappedSuperclass
public abstract class Conta {
    
    private Date emissao;

    private Date vencimento;

    private Float valor;
    
}
