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

import com.senac.aesthetics.domains.Fornecedor;
import com.senac.aesthetics.domains.Pessoa;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.ExcecaoRegraNegocio;
import com.senac.aesthetics.errors.Erros;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.interfaces.InterfaceVerificarPessoaJaCadastrada;
import com.senac.aesthetics.repositories.FornecedorRepository;

@Service
public class FornecedorService implements InterfaceGenericaResource<Fornecedor> {

    // Objetos:
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private InterfaceVerificarPessoaJaCadastrada pessoaService;

    // Métodos:
    public Page<Fornecedor> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return fornecedorRepository.findAll(pagina);
    }

    public Fornecedor obterPorId(Long idFornecedor) throws Exception {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent()) {
            return fornecedor.get();
        } else {
            throw new NoSuchElementException("Fornecedor Não Encontrado! ID: " + idFornecedor);
        }
    }

    public Fornecedor inserir(Fornecedor fornecedor) throws Exception {
        this.validarFornecedor(fornecedor);
        this.associarFornecedorAPessoaJaCadastrada(fornecedor);

        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Fornecedor fornecedor) throws Exception {
        if (fornecedorRepository.existsById(fornecedor.getId())) {
            return fornecedorRepository.saveAndFlush(fornecedor);
        } else {
            throw new NoSuchElementException("Fornecedor Não Encontrado! ID: " + fornecedor.getId());
        }
    }

    public void excluir(Long idFornecedor) throws Exception {
        if (fornecedorRepository.existsById(idFornecedor)) {
            fornecedorRepository.deleteById(idFornecedor);
        } else {
            throw new NoSuchElementException("Fornecedor Não Encontrado! ID: " + idFornecedor);
        }
    }

    private void associarFornecedorAPessoaJaCadastrada(Fornecedor fornecedor) throws Exception {
        Optional<Pessoa> pessoaJaCadastrada = pessoaService
                .verificarPessoaJaCadastrada(fornecedor.getPessoa().getCpfOuCnpj());

        if (pessoaJaCadastrada.isPresent()) {
            fornecedor.setPessoa(pessoaJaCadastrada.get());
        }
    }

    private void validarFornecedor(Fornecedor fornecedor) throws Exception {
        List<String> mensagensErros = new ArrayList<String>();

        this.verificarFornecedorJaEstaCadastrado(fornecedor, mensagensErros);

        if (mensagensErros.size() > 0) {
            throw new ExcecaoRegraNegocio(
                    new Erros(mensagensErros, TipoMensagemEnum.ERROR, this.getClass().getSimpleName(),
                            HttpStatus.CONFLICT));
        }
    }

    private void verificarFornecedorJaEstaCadastrado(Fornecedor fornecedor, List<String> mensagensErros) {
        Optional<Fornecedor> fornecedoraCadastrada = fornecedorRepository
                .obterPorCpfOuCnpj(fornecedor.getPessoa().getCpfOuCnpj());

        if (fornecedoraCadastrada.isPresent()) {
            mensagensErros.add("Fornecedor Já Cadastrado! CPF: " + fornecedor.getPessoa().getCpfOuCnpj());
        }
    }

}
