package com.senac.aesthetics.domains;

import com.senac.aesthetics.domains.enums.EstadosBrasileirosEnum;
import com.senac.aesthetics.domains.enums.TipoPessoaEnum;
import com.senac.aesthetics.validations.anotations.CPFCNPJ;
import com.senac.aesthetics.validations.anotations.Telefone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "PESSOAS")
@CPFCNPJ(message = "O CPF/CNPJ da Pessoa Deve Ser Válido e Deve Conter a Pontuação! CPF: 123.456.789-01 / CNPJ: 12.345.678/9012-34")
public class Pessoa {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PESSOA")
    private Long id;

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

    @Column(name = "ESTADO_BRASILEIRO", length = 25)
    @Enumerated(EnumType.STRING)
    private EstadosBrasileirosEnum estadoBrasileiro;

    @Column(name = "TIPO_PESSOA", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPessoaEnum tipoPessoa;

    @Column(name = "CPF_CNPJ", length = 18, nullable = false)
    @NotBlank(message = "O CPF/CNPJ da Pessoa Deve Ser Informado e Não Pode Estar Vazio!")
    @Size(min = 14, max = 18, message = "O CPF/CNPJ da Pessoa Deve Ter 14 (CPF) ou 18 (CNPJ) Caracteres! O CPF/CNPJ da Pessoa Deve Ser Válido e Deve Conter a Pontuação! CPF: 123.456.789-01 / CNPJ: 12.345.678/9012-34")
    private String cpfOuCnpj;

}