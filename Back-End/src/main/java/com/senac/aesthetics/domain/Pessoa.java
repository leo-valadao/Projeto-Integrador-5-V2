package com.senac.aesthetics.domain;

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
public abstract class Pessoa {
    
    private String nome;

    private String telefone;

    private String email;

    private String uf;

}
