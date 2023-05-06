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
@Entity(name = "Funcionário")
@Table(name = "FUNCIONARIOS")
public class Funcionario extends Pessoa {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    private String login;

    private String senha;

    private Float comissao;

    // Relacionamentos:

    private Set<Agendamento> agendamentos;

    private Set<OrdemServico> respPeloLancamento;

    private Set<OrdemServico> respExecServico;

}
