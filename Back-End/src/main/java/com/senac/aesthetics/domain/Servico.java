package com.senac.aesthetics.domain;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor

// Java Persistence API:
@Entity(name = "Serviço")
@Table(name = "SERVICOS")
public class Servico {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICO")
    private Long id;
    
    private String nome;
    
    private String descricao;
    
    private Float precoCusto;
    
    private Float precoVenda;

    // Relacionamentos:
    private Set<Agendamento> agendamentos;

    private Set<OrdemServico> ordensDeServicos;

}
