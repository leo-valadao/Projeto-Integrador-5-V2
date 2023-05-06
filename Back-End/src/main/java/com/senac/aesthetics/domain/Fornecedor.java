package com.senac.aesthetics.domain;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor

// Java Persistence API:
@Entity(name = "Fornecedor")
@Table(name = "FORNECEDORES")
public class Fornecedor extends Pessoa {
    
    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FORNECEDOR")
    private Long id;

    // Relacionamentos:
    @OneToMany(orphanRemoval = false, mappedBy = "fornecedor")
    @JoinColumn(name = "ID_CONTA_PAGAR_FK", referencedColumnName = "ID_CONTA_PAGAR")
    private Set<ContaPagar> contasPagar;
    
}
