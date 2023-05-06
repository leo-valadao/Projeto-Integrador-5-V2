package com.senac.aesthetics.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor

// Java Persistence API:
@MappedSuperclass
public abstract class Conta {

    // Atributos:
    @Column(name = "EMISSAO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @PastOrPresent(message = "A Data da Emissão da Conta Deve Ser Uma Data no Presente ou no Passado!")
    @NotNull(message = "A Data da Emissão da Conta Deve Ser Informada!")
    private Date emissao;

    @Column(name = "VENCIMENTO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @FutureOrPresent(message = "A Data do Vencimento da Conta Deve Ser Uma Data no Presente ou no Futuro!")
    @NotNull(message = "A Data do Vencimento da Conta Deve Ser Informada!")
    private Date vencimento;

    @Column(name = "VALOR", nullable = false, precision = 10, scale = 2)
    @Digits(integer = 10, fraction = 2, message = "O Valor Máximo da Conta é de R$ 1.000.000,00 (1 Milhão)!")
    @PositiveOrZero(message = "O Valor da Conta Deve Estar Entre R$ 0 (Zero) e R$ 1.000.000,00 (1 Milhão)!")
    @NotNull(message = "O Valor da Conta Deve Ser Informado!")
    private BigDecimal valor;

}
