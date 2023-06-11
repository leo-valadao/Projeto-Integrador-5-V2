package com.senac.aesthetics.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.ContaPagar;
import com.senac.aesthetics.domains.filters.ContaPagarFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.ContaPagarRepository;

@Service
public class ContaPagarService implements InterfaceGenericaResource<ContaPagar, ContaPagarFiltro> {

    // Objetos:
    @Autowired
    private ContaPagarRepository contaPagarRepository;

    // Métodos:
    public Page<ContaPagar> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor, ContaPagarFiltro filtro) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return contaPagarRepository.obterPorFiltroComPaginacao(filtro, pagina);
    }

    public ContaPagar obterPorId(Long idContaPagar) throws Exception {
        Optional<ContaPagar> contaPagar = contaPagarRepository.findById(idContaPagar);

        if (contaPagar.isPresent()) {
            return contaPagar.get();
        } else {
            throw new NoSuchElementException("ContaPagar Não Encontrado! ID: " + idContaPagar);
        }
    }

    public ContaPagar salvar(ContaPagar contaPagar) throws Exception {
        return contaPagarRepository.save(contaPagar);
    }

    public ContaPagar atualizar(ContaPagar contaPagar) throws Exception {
        if (contaPagarRepository.existsById(contaPagar.getId())) {
            return contaPagarRepository.saveAndFlush(contaPagar);
        } else {
            throw new NoSuchElementException("ContaPagar Não Encontrado! ID: " + contaPagar.getId());
        }
    }

    public void excluir(Long idContaPagar) throws Exception {
        if (contaPagarRepository.existsById(idContaPagar)) {
            contaPagarRepository.deleteById(idContaPagar);
        } else {
            throw new NoSuchElementException("ContaPagar Não Encontrado! ID: " + idContaPagar);
        }
    }

}
