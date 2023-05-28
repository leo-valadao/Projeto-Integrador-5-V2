package com.senac.aesthetics.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusContaPagarEnum {

    ABERTO("Aberto"),
    CANCELADO("Cancelado"),
    PAGO("Pago");

    private String status;

}
