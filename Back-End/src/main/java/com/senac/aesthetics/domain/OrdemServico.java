package com.senac.aesthetics.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senac.aesthetics.domain.enums.StatusOrdemServicoEnum;

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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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

    @Column(name = "DATA_HORA_INICIO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "A Data e Horário do Início da Ordem de Serviço Deve Ser Informada!")
    private Date dataHoraInicio;

    @Column(name = "DATA_HORA_TERMINO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "A Data e Horário do Término da Ordem de Serviço Deve Ser Informada!")
    private Date dataHoraTermino;

    @Column(name = "STATUS", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Status da Ordem de Serviço Deve Ser Informado!")
    @Size(max = 15, message = "O Tamanho Máximo do Status da Ordem de Serviço é de 15 Caracteres!")
    @Pattern(regexp = "^(ABERTO|CANCELADO|EM_EXECUCAO|CONCLUIDO)$", message = "O Status da Ordem de Serviço Só Pode Ser: ABERTO ou CANCELADO ou EM_EXECUCAO ou CONCLUIDO!")
    private StatusOrdemServicoEnum status;

    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "O Valor Máximo da Ordem de Serviço é de R$ 1.000.000,00 (1 Milhão)!")
    @PositiveOrZero(message = "O Valor da Ordem de Serviço Deve Estar Entre R$ 0 (Zero) e R$ 1.000.000,00 (1 Milhão)!")
    @NotNull(message = "O Valor da Ordem de Serviço Deve Ser Informado!")
    private BigDecimal valor;

    // Relacionamentos:
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_AGENDAMENTO_FK", referencedColumnName = "ID_AGENDAMENTO")
    private Agendamento agendamento;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_SERVICO_FK", referencedColumnName = "ID_SERVICO")
    private Servico servico;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_FUNCIONARIO_RESPONSAVEL_OS_FK", referencedColumnName = "ID_FUNCIONARIO")
    private Funcionario respOS;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_FUNCIONARIO_EXECUTA_SERVICO_FK", referencedColumnName = "ID_FUNCIONARIO")
    private Funcionario execServico;
    
}
