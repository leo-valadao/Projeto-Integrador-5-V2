package com.senac.aesthetics.domains.abstracts;

import org.hibernate.validator.constraints.br.CNPJ;

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
public abstract class PessoaJuridica extends Pessoa {
    
    // Atributos:
    @Column(name = "CNPJ", length = 18, nullable = false, unique = true)
    @NotBlank(message = "O CNPJ da Pessoa Jurídica Deve Ser Informado e Não Pode Estar Vazio!")
    @CNPJ(message = "O CNPJ Deve Ser Válido e Seguir o Formato: '12.345.678/9123-45'!")
    @Size(min = 14, max = 18, message = "O Tamanho do CNPJ da Pessoa Jurídica é de 14 á 18 Caracteres!")
    private String cnpj;

}
