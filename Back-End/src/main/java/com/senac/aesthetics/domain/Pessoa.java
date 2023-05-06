package com.senac.aesthetics.domain;

import com.senac.aesthetics.anotation.Telefone;
import com.senac.aesthetics.enums.EstadosBrasileirosEnum;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor

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

    @Column(name = "E-MAIL", length = 50)
    @Size(max = 50, message = "O Tamanho Máximo do E-Mail da Pessoa é de 50 Caracteres!")
    @Email(message = "O E-Mail da Pessoa Está Inválido!")
    private String email;

    @Column(name = "UF", length = 2)
    @Enumerated(EnumType.STRING)
    @Size(min = 2, max = 2, message = "A UF da Pessoa Deve Ser a Sigla (2 Caracteres) do Estado Brasileiro!")
    private EstadosBrasileirosEnum uf;

}