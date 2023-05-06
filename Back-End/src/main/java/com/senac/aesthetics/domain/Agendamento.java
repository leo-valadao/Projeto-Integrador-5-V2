package com.senac.aesthetics.domain;

import java.util.Date;

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
@Entity(name = "Agendamento")
@Table(name = "AGENDAMENTOS")
public class Agendamento {
    
    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGENDAMENTO")
    private Long id;

    private Date data;
    
    private Date hora;
    
    private String status;
    
    private String observacao;

    // Relacionamentos:
    
    private Cliente cliente;
    
    private Funcionario respAgendamento;
    
    private Servico servico;

    private OrdemServico ordemServico;

}
