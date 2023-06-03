package com.senac.aesthetics.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoMensagemEnum {

    SUCESS("sucess"),
    INFO("info"),
    WARN("warn"),
    ERROR("error");

    private String tipo;

}
