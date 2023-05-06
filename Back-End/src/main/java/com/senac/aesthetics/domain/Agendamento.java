package com.senac.aesthetics.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senac.aesthetics.enums.StatusAgendamentoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Column(name = "DATA", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "A Data do Agendamento Deve Ser Informada!")
    private Date data;

    @Column(name = "HORA", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull(message = "A Hora do Agendamento Deve Ser Informada!")
    private Date hora;

    @Column(name = "STATUS", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Status do Agendamento Deve Ser Informado!")
    @Size(max = 15, message = "O Tamanho Máximo do Status do Agendamento é de 15 Caracteres!")
    @Pattern(regexp = "^(ABERTO|CANCELADO|CONFIRMADO)$", message = "O Status do Agendamento Só Pode Ser: ABERTO ou CANCELADO ou CONFIRMADO!")
    private StatusAgendamentoEnum status;

    @Column(name = "OBSERVACAO", length = 500)
    @Size(max = 500, message = "O Tamanho Máximo da Observação do Agendamento é de 500 Caracteres!")
    private String observacao;

    // Relacionamentos:
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_CLIENTE_FK", referencedColumnName = "ID_CLIENTE")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_RESPONSAVEL_AGENDAMENTO_FK", referencedColumnName = "ID_FUNCIONARIO")
    private Funcionario respAgendamento;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_SERVICO_FK", referencedColumnName = "ID_SERVICO")
    private Servico servico;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "agendamento")
    @JoinColumn(name = "ID_ORDEM_SERVICO_FK", referencedColumnName = "ID_ORDEM_SERVICO")
    private OrdemServico ordemServico;

}
