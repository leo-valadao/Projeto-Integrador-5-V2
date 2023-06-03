package com.senac.aesthetics.errors;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.senac.aesthetics.domains.enums.TipoMensagemEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroGenerico {

    // Atributos:
    private List<String> mensagens;
    private TipoMensagemEnum tipoMensagem;
    private String nomeDaExcecao;
    private HttpStatus httpStatus;

    public ErroGenerico() {
    }

    public ErroGenerico(String mensagem, TipoMensagemEnum tipoMensagem, String nomeDaExcecao, HttpStatus httpStatus) {
        this.mensagens = Arrays.asList(mensagem);
        this.tipoMensagem = tipoMensagem;
        this.nomeDaExcecao = nomeDaExcecao;
        this.httpStatus = httpStatus;
    }

    public ErroGenerico(List<String> mensagens, TipoMensagemEnum tipoMensagem, String nomeDaExcecao,
            HttpStatus httpStatus) {
        this.mensagens = mensagens;
        this.tipoMensagem = tipoMensagem;
        this.nomeDaExcecao = nomeDaExcecao;
        this.httpStatus = httpStatus;
    }

}
