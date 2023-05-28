package com.senac.aesthetics.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusContaReceberEnum {

    ABERTO("Aberto"),
    CANCELADO("Cancelado"),
    RECEBIDO("Recebido");

    private String status;

}
