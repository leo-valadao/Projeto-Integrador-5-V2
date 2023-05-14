package com.senac.aesthetics.domains.abstracts;

import com.senac.aesthetics.validations.anotations.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
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
public abstract class PessoaFisica extends Pessoa {

    // Atributos:
    @Column(name = "CPF", length = 14, nullable = false, unique = true)
    @NotBlank(message = "O CPF da Pessoa Física Deve Ser Informado e Não Pode Estar Vazio!")
    @CPF(message = "O CPF da Pessoa Física Deve Ser Válido e Seguir o Formato: '123.456.789-10'!")
    @Size(min = 11, max = 14, message = "O Tamanho do CPF da Pessoa Física é de 11 á 14 Caracteres!")
    private String cpf;

}
