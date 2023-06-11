package com.senac.aesthetics.domains.filters;

import com.senac.aesthetics.domains.enums.EstadosBrasileirosEnum;
import com.senac.aesthetics.domains.enums.TipoPessoaEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaFiltro {

  private Long id = 0L;
  private String nome = "";
  private String telefone = "";
  private String email = "";
  private EstadosBrasileirosEnum estadoBrasileiro = null;
  private TipoPessoaEnum tipoPessoa = null;
  private String cpfOuCnpj = "";

}
