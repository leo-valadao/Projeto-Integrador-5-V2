package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Pessoa;
import com.senac.aesthetics.interfaces.InterfaceServiceVerificarPessoaJaCadastrada;
import com.senac.aesthetics.repositories.PessoaRepository;

@Service
public class PessoaService implements InterfaceServiceVerificarPessoaJaCadastrada {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Override
  public Optional<Pessoa> verificarPessoaJaCadastrada(String cpfOuCnpj) {
    return pessoaRepository.findByCpfOuCnpj(cpfOuCnpj);
  }

}
