package com.senac.aesthetics.domains;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senac.aesthetics.domains.enums.StatusOrdemServicoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// Java Persistence API:
@Entity
@Table(name = "ORDENS_SERVICOS")
public class OrdemServico {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDEM_SERVICO")
    private Long id;

    @Column(name = "DATA_HORA_INICIO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "A Data e Horário do Início da Ordem de Serviço Deve Ser Informada!")
    private Date dataHoraInicio;

    @Column(name = "DATA_HORA_TERMINO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "A Data e Horário do Término da Ordem de Serviço Deve Ser Informada!")
    private Date dataHoraTermino;

    @Column(name = "STATUS", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Status da Ordem de Serviço Deve Ser Informado!")
    private StatusOrdemServicoEnum status;

    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "O Valor Máximo da Ordem de Serviço é de R$ 1.000.000,00 (1 Milhão)!")
    @PositiveOrZero(message = "O Valor da Ordem de Serviço Deve Estar Entre R$ 0 (Zero) e R$ 1.000.000,00 (1 Milhão)!")
    @NotNull(message = "O Valor da Ordem de Serviço Deve Ser Informado!")
    private BigDecimal valor;

    // Relacionamentos:
    @OneToOne
    @JoinColumn(name = "ID_AGENDAMENTO_FK")
    @NotNull(message = "O Agendamento da Ordem de Serviço Deve Ser Informado!")
    private Agendamento agendamento;

    @ManyToOne
    @JoinColumn(name = "ID_SERVICO_FK")
    @NotNull(message = "O Serviço da Ordem de Serviço Deve Ser Informado!")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO_RESPONSAVEL_OS_FK")
    @NotNull(message = "O Reponsável Pelo Lançamento da Ordem de Serviço Deve Ser Informado!")
    private Funcionario responsavelPelaOS;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONARIO_EXECUTAR_SERVICO_FK")
    private Funcionario executorServico;

}
