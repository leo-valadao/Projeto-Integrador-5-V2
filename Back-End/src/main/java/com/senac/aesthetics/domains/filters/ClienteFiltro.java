package com.senac.aesthetics.domains.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltro {

  private Long id = 0L;
  private String alergias = "";
  private PessoaFiltro pessoa = new PessoaFiltro();

}
