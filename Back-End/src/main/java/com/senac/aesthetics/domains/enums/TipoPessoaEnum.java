package com.senac.aesthetics.domains.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPessoaEnum {

  PESSOA_FISICA("Pessoa Física"),
  PESSOA_JURIDICA("Pessoa Jurídica");

  private String tipo;

}
