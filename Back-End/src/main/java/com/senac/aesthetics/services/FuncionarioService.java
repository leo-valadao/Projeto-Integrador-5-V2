package com.senac.aesthetics.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Funcionario;
import com.senac.aesthetics.domains.Pessoa;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.ExcecaoRegraNegocio;
import com.senac.aesthetics.errors.Erros;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.interfaces.InterfaceVerificarPessoaJaCadastrada;
import com.senac.aesthetics.repositories.FuncionarioRepository;

@Service
public class FuncionarioService implements InterfaceGenericaResource<Funcionario> {

    // Objetos:
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private InterfaceVerificarPessoaJaCadastrada pessoaService;

    // Métodos:
    public Page<Funcionario> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return funcionarioRepository.findAll(pagina);
    }

    public Funcionario obterPorId(Long idFuncionario) throws Exception {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

        if (funcionario.isPresent()) {
            return funcionario.get();
        } else {
            throw new NoSuchElementException("Funcionario Não Encontrado! ID: " + idFuncionario);
        }
    }

    public Funcionario inserir(Funcionario funcionario) throws Exception {
        this.validarFuncionario(funcionario);
        this.associarFuncionarioAPessoaJaCadastrada(funcionario);

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Funcionario funcionario) throws Exception {
        if (funcionarioRepository.existsById(funcionario.getId())) {
            return funcionarioRepository.saveAndFlush(funcionario);
        } else {
            throw new NoSuchElementException("Funcionario Não Encontrado! ID: " + funcionario.getId());
        }
    }

    public void excluir(Long idFuncionario) throws Exception {
        if (funcionarioRepository.existsById(idFuncionario)) {
            funcionarioRepository.deleteById(idFuncionario);
        } else {
            throw new NoSuchElementException("Funcionario Não Encontrado! ID: " + idFuncionario);
        }
    }

    private void associarFuncionarioAPessoaJaCadastrada(Funcionario funcionario) throws Exception {
        Optional<Pessoa> pessoaJaCadastrada = pessoaService
                .verificarPessoaJaCadastrada(funcionario.getPessoa().getCpfOuCnpj());

        if (pessoaJaCadastrada.isPresent()) {
            funcionario.setPessoa(pessoaJaCadastrada.get());
        }
    }

    private void validarFuncionario(Funcionario funcionario) throws Exception {
        List<String> mensagensErros = new ArrayList<String>();

        this.verificarFuncionarioJaEstaCadastrado(funcionario, mensagensErros);

        if (mensagensErros.size() > 0) {
            throw new ExcecaoRegraNegocio(
                    new Erros(mensagensErros, TipoMensagemEnum.ERROR, this.getClass().getSimpleName(),
                            HttpStatus.CONFLICT));
        }
    }

    private void verificarFuncionarioJaEstaCadastrado(Funcionario funcionario, List<String> mensagensErros) {
        Optional<Funcionario> funcionarioaCadastrada = funcionarioRepository
                .obterPorCpfOuCnpj(funcionario.getPessoa().getCpfOuCnpj());

        if (funcionarioaCadastrada.isPresent()) {
            mensagensErros.add("Funcionario Já Cadastrado! CPF: " + funcionario.getPessoa().getCpfOuCnpj());
        }
    }

}
