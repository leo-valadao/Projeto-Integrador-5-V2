package com.senac.aesthetics.errors;

import com.senac.aesthetics.domains.enums.TipoMensagemEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataBaseException extends RuntimeException {

    // Atributos:
    private TipoMensagemEnum tipo;
    private String mensagem;
    
}