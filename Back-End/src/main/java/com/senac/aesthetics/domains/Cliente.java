package com.senac.aesthetics.domains;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senac.aesthetics.domains.abstracts.Pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Cliente extends Pessoa {

    // Atributos:
    @Column(name = "ALERGIAS", length = 100)
    @Size(max = 100, message = "O Tamanho Máximo das Alergias do Cliente é de 100 Caracteres!")
    private String alergias;

    // Relacionamentos:
    @OneToMany(orphanRemoval = false, mappedBy = "cliente")
    @JsonIgnore
    private List<Agendamento> agendamentos;

}
