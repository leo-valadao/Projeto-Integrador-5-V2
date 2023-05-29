package com.senac.aesthetics.errors;

import org.springframework.dao.DataIntegrityViolationException;
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
        "Erro Interno no Servidor! \n Entre em Contato a Equipe do Aesthetics em service-desk@aesthetics.com!",
        TipoMensagemEnum.ERROR);
    return ResponseEntity.internalServerError().body(erro);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErroGenerico> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    StringBuilder erros = new StringBuilder();
    for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
      erros.append(erro.getDefaultMessage() + "\n");
    }
    ErroGenerico erro = new ErroGenerico(erros.toString().trim(), TipoMensagemEnum.ERROR);
    return ResponseEntity.internalServerError().body(erro);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  protected ResponseEntity<ErroGenerico> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    ErroGenerico erro = new ErroGenerico(
        "Não foi possível executar a ação pois o objeto esta ligado a outros objetos!",
        TipoMensagemEnum.ERROR);
    return ResponseEntity.badRequest().body(erro);
  }
}