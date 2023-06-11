package com.senac.aesthetics.domains;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Entity
@Table(name = "FUNCIONARIOS")
public class Funcionario {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    @Column(name = "LOGIN", nullable = false, length = 30, unique = true)
    @NotBlank(message = "O Login do Funcionário Deve Ser Informado e Não Pode Estar Vazio!")
    @Size(max = 30, message = "O Tamanho Máximo do Login do Funcionário é de 30 Caracteres!")
    private String login;

    @Column(name = "SENHA", nullable = false, length = 30)
    @NotBlank(message = "A Senha do Funcionário Deve Ser Informado e Não Pode Estar Vazio!")
    @Size(max = 30, message = "O Tamanho Máximo da Senha do Funcionário é de 30 Caracteres!")
    private String senha;

    @Column(name = "COMISSAO", nullable = false, precision = 3, scale = 2)
    @Digits(integer = 3, fraction = 2, message = "A Comissão do Funcionário Deve Estar Entre 0% á 100%!")
    @Max(value = 100, message = "A Comissão do Funcionário Máxima é de 100%!")
    @Min(value = 100, message = "A Comissão do Funcionário Mínimia é de 0%!")
    @PositiveOrZero(message = "A Comissão do Funcionário Deve Estar Entre 0% á 100%!")
    @NotNull(message = "O Valor da Comissão Deve Ser Informado!")
    private BigDecimal comissao;

    // Relacionamentos:
    @OneToOne(fetch = FetchType.EAGER, optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PESSOA_FK", unique = true, nullable = false)
    private Pessoa pessoa;

    @OneToMany(orphanRemoval = false, mappedBy = "funcionario")
    @JsonIgnore
    private List<Agendamento> agendamentos;

    @OneToMany(orphanRemoval = false, mappedBy = "responsavelPelaOS")
    @JsonIgnore
    private List<OrdemServico> responsavelPeloLancamento;

    @OneToMany(orphanRemoval = false, mappedBy = "executorServico")
    @JsonIgnore
    private List<OrdemServico> responsavelExecutarServico;

}
