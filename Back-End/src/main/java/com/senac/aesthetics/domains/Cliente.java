package com.senac.aesthetics.domains;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "CLIENTES")
public class Cliente {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "ALERGIAS", length = 100)
    @Size(max = 100, message = "O Tamanho Máximo das Alergias do Cliente é de 100 Caracteres!")
    private String alergias;

    // Relacionamentos:
    @OneToOne(fetch = FetchType.EAGER, optional = false, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PESSOA_FK", unique = true, nullable = false)
    private Pessoa pessoa;

    @OneToMany(orphanRemoval = false, mappedBy = "cliente")
    @JsonIgnore
    private List<Agendamento> agendamentos;

}
