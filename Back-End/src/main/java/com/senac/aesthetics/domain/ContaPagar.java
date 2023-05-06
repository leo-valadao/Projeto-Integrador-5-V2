package com.senac.aesthetics.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.senac.aesthetics.domain.enums.StatusContaPagarEnum;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor

// Java Persistence API:
@Entity(name = "Conta a Pagar")
@Table(name = "CONTAS_PAGAR")
public class ContaPagar extends Conta {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTA_PAGAR")
    private Long id;

    @Column(name = "VALOR_PAGO", precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "O Valor Máximo do Pagamento da Conta é de R$ 1.000.000,00 (1 Milhão)!")
    @PositiveOrZero(message = "O Valor do Pagamento da Conta Deve Estar Entre R$ 0 (Zero) e R$ 1.000.000,00 (1 Milhão)!")
    private Float valorPago;

    @Column(name = "DATA_PAGAMENTO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @PastOrPresent(message = "A Data de Pagamaneto da Conta Deve Ser Uma Data no Presente ou no Passado!")
    private Date pagamento;

    @Column(name = "STATUS", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Status da Conta a Pagar Deve Ser Informado!")
    @Size(max = 15, message = "O Tamanho Máximo do Status da Conta a Pagar é de 15 Caracteres!")
    @Pattern(regexp = "^(ABERTO|CANCELADO|PAGO)$", message = "O Status da Conta a Pagar Só Pode Ser: ABERTO ou CANCELADO ou PAGO!")
    private StatusContaPagarEnum status;

    // Relacionamentos:
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_FORNECEDOR_FK", referencedColumnName = "ID_FORNECEDOR")
    private Fornecedor fornecedor;

}
