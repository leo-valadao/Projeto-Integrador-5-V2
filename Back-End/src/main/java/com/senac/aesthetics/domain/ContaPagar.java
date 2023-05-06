package com.senac.aesthetics.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok:
@Getter
@Setter
@NoArgsConstructor

// Java Persistence API:
@Entity(name = "Conta a Pagar")
@Table(name = "CONTAS_PAGAR")
public class ContaPagar extends Conta {
    
    // Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTA_PAGAR")
    private Long id;

    private Float valorPago;

    private Date pagamento;

    private int status;

    // Relacionamentos:

    private Fornecedor fornecedor;

}
