package com.senac.aesthetics.domain;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor

// Java Persistence API:
@Entity(name = "Clinte")
@Table(name = "CLIENTES")
public class Cliente extends Pessoa {

    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "ALERGIAS", length = 100)
    @Size(max = 100, message = "O Tamanho Máximo das Alergias do Cliente é de 100 Caracteres!")
    private String alergias;

    // Relacionamentos:
    @OneToMany(orphanRemoval = false, mappedBy = "cliente")
    @JoinColumn(name = "ID_AGENDAMENTO_FK", referencedColumnName = "ID_AGENDAMENTO")
    private Set<Agendamento> agendamentos;

}
