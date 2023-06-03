package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.abstracts.Pessoa;
import com.senac.aesthetics.interfaces.InterfaceVerificarPessoaJaCadastrada;
import com.senac.aesthetics.repositories.PessoaRepository;

@Service
public class PessoaService implements InterfaceVerificarPessoaJaCadastrada {

  @Autowired
  private PessoaRepository pessoaRepository;

  @Override
  public Optional<Pessoa> verificarPessoaJaCadastrada(String cpfOuCnpj) throws Exception {
    return pessoaRepository.findByCpfOuCnpj(cpfOuCnpj);
  }

}
