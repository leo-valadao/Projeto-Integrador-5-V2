package com.senac.aesthetics.domain;

import java.util.Date;
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
@Entity(name = "Ordem Serviço")
@Table(name = "ORDENS_SERVICOS")
public class OrdemServico {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEM_SERVICO")
    private Long id;

    private Date dataHoraInicio;

    private Date dataHoraTermino;

    private String status;

    private Float valor;

    // Relacionamentos:

    private Agendamento agendamento;

    private Servico servico;

    private Funcionario respOS;

    private Funcionario execServico;

    private Set<ContaReceber> contasReceber;
    
}
