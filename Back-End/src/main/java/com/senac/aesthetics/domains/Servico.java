package com.senac.aesthetics.domains;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.Positive;
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
@Table(name = "SERVICOS")
public class Servico {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICO")
    private Long id;

    @Column(name = "NOME", length = 100, nullable = false)
    @NotBlank(message = "O Nome do Serviço Deve Ser Informado e Não Pode Estar Vazio!")
    @Size(max = 100, message = "O Tamanho Máximo da Nome do Serviço é de 100 Caracteres!")
    private String nome;

    @Column(name = "DESCRICAO", length = 500)
    @Size(max = 500, message = "O Tamanho Máximo da Descrição do Serviço é de 500 Caracteres!")
    private String descricao;

    @Column(name = "PRECO_CUSTO", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "O Valor Máximo do Preço de Custo do Serviço é de R$ 1.000.000,00 (1 Milhão)!")
    @Positive(message = "O Valor do Preço de Custo do Serviço Deve Estar Entre R$ 0 (Zero) e R$ 1.000.000,00 (1 Milhão)!")
    @NotNull(message = "O Valor do Preço de Custo do Serviço Deve Ser Informado!")
    private BigDecimal precoCusto;

    @Column(name = "PRECO_VENDA", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "O Valor Máximo do Preço de Venda do Serviço é de R$ 1.000.000,00 (1 Milhão)!")
    @Positive(message = "O Valor do Preço de Venda do Serviço Deve Estar Entre R$ 0 (Zero) e R$ 1.000.000,00 (1 Milhão)!")
    @NotNull(message = "O Valor do Preço de Venda do Serviço Deve Ser Informado!")
    private BigDecimal precoVenda;

    // Relacionamentos:
    @OneToMany(orphanRemoval = false, mappedBy = "servico")
    @JsonIgnore
    private List<Agendamento> agendamentos;

    @OneToMany(orphanRemoval = false, mappedBy = "servico")
    @JsonIgnore
    private List<OrdemServico> ordensDeServicos;

}
