package com.senac.aesthetics.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExcecaoRegraNegocio extends Exception {

  private Erros erroGenerico;

}
