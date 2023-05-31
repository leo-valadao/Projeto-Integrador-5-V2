package com.senac.aesthetics.errors;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.senac.aesthetics.domains.enums.TipoMensagemEnum;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErroGenerico> handleException(Exception ex) {
    ErroGenerico erro = new ErroGenerico(
        "Erro Interno no Servidor! Entre em Contato a Equipe do Aesthetics em service-desk@aesthetics.com!",
        TipoMensagemEnum.ERROR, ex.getClass().getSimpleName());
    return new ResponseEntity<ErroGenerico>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NoSuchElementException.class)
  protected ResponseEntity<ErroGenerico> handleNoSuchElementException(
      NoSuchElementException ex) {
    ErroGenerico erro = new ErroGenerico(ex.getMessage(), TipoMensagemEnum.ERROR, ex.getClass().getSimpleName());
    return new ResponseEntity<ErroGenerico>(erro, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErroGenerico> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    StringBuilder erros = new StringBuilder();
    for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
      erros.append(erro.getDefaultMessage() + " \n ");
    }
    IllegalArgumentException excecao = new IllegalArgumentException(erros.toString().trim());
    ErroGenerico erro = new ErroGenerico(excecao.getMessage(), TipoMensagemEnum.ERROR, excecao.getClass()
        .getSimpleName());
    return new ResponseEntity<ErroGenerico>(erro, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<ErroGenerico> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    ErroGenerico erro = new ErroGenerico("Violação da integridade dos dados!",
        TipoMensagemEnum.ERROR, ex.getClass().getSimpleName());
    return new ResponseEntity<ErroGenerico>(erro, HttpStatus.CONFLICT);
  }

}