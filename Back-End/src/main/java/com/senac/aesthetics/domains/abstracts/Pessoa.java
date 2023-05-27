package com.senac.aesthetics.domains.abstracts;

import com.senac.aesthetics.domains.enums.EstadosBrasileirosEnum;
import com.senac.aesthetics.validations.anotations.Telefone;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@MappedSuperclass
public abstract class Pessoa {

    // Atributos:
    @Column(name = "NOME", length = 100, nullable = false)
    @NotBlank(message = "O Nome da Pessoa Deve Ser Informado e Não Pode Estar Vazio!")
    @Size(max = 100, message = "O Tamanho Máximo da Nome da Pessoa é de 100 Caracteres!")
    private String nome;

    @Column(name = "TELEFONE", length = 14, nullable = false)
    @NotBlank(message = "O Telefone da Pessoa Deve Ser Informado e Não Pode Estar Vazio!")
    @Size(min = 13, max = 14, message = "O Tamanho do Telefone da Pessoa é de 13 ou 14 Caracteres!")
    @Telefone(message = "O Telefone da Pessoa Está Inválido!")
    private String telefone;

    @Column(name = "EMAIL", length = 50)
    @Size(max = 50, message = "O Tamanho Máximo do E-Mail da Pessoa é de 50 Caracteres!")
    @Email(message = "O E-Mail da Pessoa Está Inválido!")
    private String email;

    @Column(name = "ESTADO_BRASILEIRO", length = 2)
    @Enumerated(EnumType.STRING)
    private EstadosBrasileirosEnum estadoBrasileiro;

}