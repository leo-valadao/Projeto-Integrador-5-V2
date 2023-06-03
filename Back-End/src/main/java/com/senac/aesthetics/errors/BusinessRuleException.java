package com.senac.aesthetics.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessRuleException extends Exception {
  
  private ErroGenerico erroGenerico;

}
