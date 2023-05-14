package com.senac.aesthetics.domains;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.aesthetics.domains.enums.StatusAgendamentoEnum;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "AGENDAMENTOS")
public class Agendamento {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGENDAMENTO")
    private Long id;

    @Column(name = "DATA", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @NotNull(message = "A Data do Agendamento Deve Ser Informada!")
    private Date data;

    @Column(name = "HORA", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIME)
    @NotNull(message = "A Hora do Agendamento Deve Ser Informada!")
    private Date hora;

    @Column(name = "STATUS", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Status do Agendamento Deve Ser Informado!")
    private StatusAgendamentoEnum status;

    @Column(name = "OBSERVACAO", length = 500)
    @Size(max = 500, message = "O Tamanho Máximo da Observação do Agendamento é de 500 Caracteres!")
    private String observacao;

    // Relacionamentos:
    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE_FK", referencedColumnName = "ID_CLIENTE")
    @NotNull(message = "O Cliente do Agendamento Deve Ser Informado!")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "ID_RESPONSAVEL_AGENDAMENTO_FK", referencedColumnName = "ID_FUNCIONARIO")
    @NotNull(message = "O Responsável do Agendamento Deve Ser Informado!")
    private Funcionario respAgendamento;

    @ManyToOne
    @JoinColumn(name = "ID_SERVICO_FK", referencedColumnName = "ID_SERVICO")
    @NotNull(message = "O Serviço do Agendamento Deve Ser Informado!")
    private Servico servico;

    @OneToOne(mappedBy = "agendamento")
    @JoinColumn(name = "ID_ORDEM_SERVICO_FK", referencedColumnName = "ID_ORDEM_SERVICO")
    @JsonIgnore
    private OrdemServico ordemServico;

}
