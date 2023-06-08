package com.senac.aesthetics.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.senac.aesthetics.domains.enums.TipoMensagemEnum;

import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Erros> handleException(Exception ex) {
    Erros erro = new Erros(
        "Erro Interno no Servidor! Entre em Contato a Equipe do Aesthetics em service-desk@aesthetics.com!",
        TipoMensagemEnum.ERROR, ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<Erros>(erro, erro.getHttpStatus());
  }

  @ExceptionHandler(ExcecaoRegraNegocio.class)
  protected ResponseEntity<Erros> handleBusinessRuleException(ExcecaoRegraNegocio ex) {
    return new ResponseEntity<Erros>(ex.getErroGenerico(), ex.getErroGenerico().getHttpStatus());
  }

  @ExceptionHandler(NoSuchElementException.class)
  protected ResponseEntity<Erros> handleNoSuchElementException(
      NoSuchElementException ex) {
    Erros erro = new Erros(ex.getMessage(), TipoMensagemEnum.ERROR,
        ex.getClass().getSimpleName(), HttpStatus.NOT_FOUND);
    return new ResponseEntity<Erros>(erro, erro.getHttpStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Erros> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    List<String> erros = new ArrayList<String>();
    for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
      erros.add(erro.getDefaultMessage());
    }
    Erros erro = new Erros(erros, TipoMensagemEnum.ERROR, ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<Erros>(erro, erro.getHttpStatus());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<Erros> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    Erros erro = new Erros("Violação da integridade dos dados!",
        TipoMensagemEnum.ERROR, ex.getClass().getSimpleName(), HttpStatus.CONFLICT);
    return new ResponseEntity<Erros>(erro, erro.getHttpStatus());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  protected ResponseEntity<Erros> handleConstraintViolationException(ConstraintViolationException ex) {
    List<String> erros = new ArrayList<String>();
    for (ConstraintViolation<?> violacao : ex.getConstraintViolations()) {
      erros.add(violacao.getMessage());
    }
    Erros erro = new Erros(erros, TipoMensagemEnum.ERROR, ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
    return new ResponseEntity<Erros>(erro, erro.getHttpStatus());
  }

}