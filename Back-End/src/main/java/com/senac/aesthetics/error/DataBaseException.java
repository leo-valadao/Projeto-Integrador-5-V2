package com.senac.aesthetics.error;

import com.senac.aesthetics.enums.TipoMensagemEnum;

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
