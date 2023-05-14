package com.senac.aesthetics.domains;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.aesthetics.domains.abstracts.Pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
@Entity(name = "Funcionário")
@Table(name = "FUNCIONARIOS")
public class Funcionario extends Pessoa {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    @Column(name = "LOGIN", nullable = false, length = 30)
    @NotBlank(message = "O Login do Funcionário Deve Ser Informado e Não Pode Estar Vazio!")
    @Size(max = 30, message = "O Tamanho Máximo do Login do Funcionário é de 30 Caracteres!")
    private String login;

    @Column(name = "SENHA", nullable = false, length = 30)
    @NotBlank(message = "A Senha do Funcionário Deve Ser Informado e Não Pode Estar Vazio!")
    @Size(max = 30, message = "O Tamanho Máximo da Senha do Funcionário é de 30 Caracteres!")
    private String senha;

    @Column(name = "COMISSAO", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "O Valor Máximo da Comissão é de R$ 1.000.000,00 (1 Milhão)!")
    @PositiveOrZero(message = "O Valor da Comissão Deve Estar Entre R$ 0 (Zero) e R$ 1.000.000,00 (1 Milhão)!")
    @NotNull(message = "O Valor da Comissão Deve Ser Informado!")
    private BigDecimal comissao;

    // Relacionamentos:
    @OneToMany(orphanRemoval = false, mappedBy = "respAgendamento")
    @JsonIgnore
    private List<Agendamento> agendamentos;

    @OneToMany(orphanRemoval = false, mappedBy = "respOS")
    @JsonIgnore
    private List<OrdemServico> respPeloLancamento;

    @OneToMany(orphanRemoval = false, mappedBy = "execServico")
    @JsonIgnore
    private List<OrdemServico> respExecServico;

}
