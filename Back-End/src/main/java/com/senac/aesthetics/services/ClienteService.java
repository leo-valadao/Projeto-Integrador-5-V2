package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Cliente;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.ErroGenerico;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.ClienteRepository;

@Service
public class ClienteService implements InterfaceGenericaResource<Cliente> {

    // Objetos:
    @Autowired
    private ClienteRepository clienteRepository;

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
            throw new ErroGenerico(("Cliente Não Encontrado! ID: " + idCliente),
                    TipoMensagemEnum.ERROR);
        }
    }

    public Cliente inserir(Cliente cliente) throws Exception {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) throws Exception {
        if (clienteRepository.existsById(cliente.getId())) {
            return clienteRepository.saveAndFlush(cliente);
        } else {
            throw new ErroGenerico(("Cliente Não Encontrado! ID: " + cliente.getId()),
                    TipoMensagemEnum.ERROR);
        }
    }

    public void excluir(Long idCliente) throws Exception {
        if (clienteRepository.existsById(idCliente)) {
            clienteRepository.deleteById(idCliente);
        } else {
            throw new ErroGenerico(("Cliente Não Encontrado! ID: " + idCliente),
                    TipoMensagemEnum.ERROR);
        }
    }

}
