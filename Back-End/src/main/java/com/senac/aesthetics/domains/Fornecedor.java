package com.senac.aesthetics.domains;

import com.senac.aesthetics.domains.abstracts.PessoaJuridica;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity(name = "Fornecedor")
@Table(name = "FORNECEDORES")
public class Fornecedor extends PessoaJuridica {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FORNECEDOR")
    private Long id;

}
