package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Cliente;
import com.senac.aesthetics.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.interfaces.IGenericaService;
import com.senac.aesthetics.repositories.ClienteRepository;

@Service
public class ClienteService implements IGenericaService<Cliente> {

    // Objetos:
    @Autowired
    private ClienteRepository clienteRepository;

    // Métodos:
    public Page<Cliente> obterTodos(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return clienteRepository.findAll(pagina);
    }

    public Cliente obterPorId(Long idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Cliente Não Encontrado! ID: " + idCliente);
        }
    }

    public Cliente inserir(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            return clienteRepository.saveAndFlush(cliente);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Cliente Não Encontrado! ID: " + cliente.getId());
        }
    }

    public void excluir(Long idCliente) {
        if (clienteRepository.existsById(idCliente)) {
            clienteRepository.deleteById(idCliente);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Cliente Não Encontrado! ID: " + idCliente);
        }
    }

}
