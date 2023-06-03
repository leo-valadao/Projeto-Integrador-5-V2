package com.senac.aesthetics.interfaces;

import java.util.Optional;

import com.senac.aesthetics.domains.abstracts.Pessoa;

public interface InterfaceVerificarPessoaJaCadastrada {

  public Optional<Pessoa> verificarPessoaJaCadastrada(String cpfOuCnpj);

}
