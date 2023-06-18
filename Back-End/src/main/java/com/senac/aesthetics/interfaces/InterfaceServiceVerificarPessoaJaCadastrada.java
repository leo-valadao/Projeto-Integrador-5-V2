package com.senac.aesthetics.interfaces;

import java.util.Optional;

import com.senac.aesthetics.domains.Pessoa;

public interface InterfaceServiceVerificarPessoaJaCadastrada {

  public Optional<Pessoa> verificarPessoaJaCadastrada(String cpfOuCnpj);

}
