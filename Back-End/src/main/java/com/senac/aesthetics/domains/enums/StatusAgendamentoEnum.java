package com.senac.aesthetics.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusAgendamentoEnum {

    ABERTO("Aberto"),
    CANCELADO("Cancelado"),
    CONFIRMADO("Confirmado");

    private String status;

}
