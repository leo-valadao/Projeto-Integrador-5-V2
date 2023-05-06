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
@Entity(name = "Conta a Receber")
@Table(name = "CONTAS_RECEBER")
public class ContaReceber extends Conta {
    
    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTA_RECEBER")
    private Long id;

    private Float valorRecebido;

    private Date recebimento;

    private String status;

    // Relacionamentos:

    private OrdemServico ordemServico;

    private Cliente cliente;

}
