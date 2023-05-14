package com.senac.aesthetics.domains;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senac.aesthetics.domains.abstracts.Conta;
import com.senac.aesthetics.enums.StatusContaReceberEnum;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
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
@Entity(name = "Conta a Receber")
@Table(name = "CONTAS_RECEBER")
public class ContaReceber extends Conta {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTA_RECEBER")
    private Long id;

    @Column(name = "VALOR_RECEBIDO", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "O Valor Máximo de Recebimento da Conta a Receber é de R$ 1.000.000,00 (1 Milhão)!")
    @PositiveOrZero(message = "O Valor Recebido da Conta a Receber Deve Estar Entre R$ 0 (Zero) e R$ 1.000.000,00 (1 Milhão)!")
    private BigDecimal valorRecebido;

    @Column(name = "DATA_RECEBIMENTO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @PastOrPresent(message = "A Data de Recebimento da Conta a Receber Deve Ser Uma Data no Presente ou no Passado!")
    private Date recebimento;

    @Column(name = "STATUS", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Status da Conta a Receber Deve Ser Informado!")
    @Size(max = 15, message = "O Tamanho Máximo do Status da Conta a Receber é de 15 Caracteres!")
    @Pattern(regexp = "^(ABERTO|CANCELADO|RECEBIDO)$", message = "O Status da Conta a Receber Só Pode Ser: ABERTO ou CANCELADO ou RECEBIDO!")
    private StatusContaReceberEnum status;

    // Relacionamentos:
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_ORDEM_SERVICO_FK", referencedColumnName = "ID_ORDEM_SERVICO")
    private OrdemServico ordemServico;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_CLIENTE_FK", referencedColumnName = "ID_CLIENTE")
    private Cliente cliente;

}
