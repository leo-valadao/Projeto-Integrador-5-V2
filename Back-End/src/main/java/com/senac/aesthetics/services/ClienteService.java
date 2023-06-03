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

import com.senac.aesthetics.domains.Cliente;
import com.senac.aesthetics.domains.Pessoa;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.ExcecaoRegraNegocio;
import com.senac.aesthetics.errors.Erros;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.interfaces.InterfaceVerificarPessoaJaCadastrada;
import com.senac.aesthetics.repositories.ClienteRepository;

@Service
public class ClienteService implements InterfaceGenericaResource<Cliente> {

    // Objetos:
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private InterfaceVerificarPessoaJaCadastrada pessoaService;

    // Métodos:
    public Page<Cliente> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return clienteRepository.findAll(pagina);
    }

    public Cliente obterPorId(Long idCliente) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new NoSuchElementException("Cliente Não Encontrado! ID: " + idCliente);
        }
    }

    public Cliente inserir(Cliente cliente) throws Exception {
        this.validarCliente(cliente);
        this.associarClienteAPessoaJaCadastrada(cliente);

        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) throws Exception {
        if (clienteRepository.existsById(cliente.getId())) {
            return clienteRepository.saveAndFlush(cliente);
        } else {
            throw new NoSuchElementException("Cliente Não Encontrado! ID: " + cliente.getId());
        }
    }

    public void excluir(Long idCliente) throws Exception {
        if (clienteRepository.existsById(idCliente)) {
            clienteRepository.deleteById(idCliente);
        } else {
            throw new NoSuchElementException("Cliente Não Encontrado! ID: " + idCliente);
        }
    }

    private void validarCliente(Cliente cliente) throws Exception {
        List<String> mensagensErros = new ArrayList<String>();

        this.verificarClienteJaEstaCadastrado(cliente, mensagensErros);

        if (mensagensErros.size() > 0) {
            throw new ExcecaoRegraNegocio(
                    new Erros(mensagensErros, TipoMensagemEnum.ERROR, this.getClass().getSimpleName(),
                            HttpStatus.CONFLICT));
        }
    }

    private void associarClienteAPessoaJaCadastrada(Cliente cliente) {
        Optional<Pessoa> pessoaJaCadastrada = pessoaService
                .verificarPessoaJaCadastrada(cliente.getPessoa().getCpfOuCnpj());

        if (pessoaJaCadastrada.isPresent()) {
            cliente.setPessoa(pessoaJaCadastrada.get());
        }
    }

    private void verificarClienteJaEstaCadastrado(Cliente cliente, List<String> mensagensErros) {
        Optional<Cliente> clienteaCadastrada = clienteRepository.obterPorCpfOuCnpj(cliente.getPessoa().getCpfOuCnpj());

        if (clienteaCadastrada.isPresent()) {
            mensagensErros.add("Cliente Já Cadastrado! CPF: " + cliente.getPessoa().getCpfOuCnpj());
        }
    }

}
