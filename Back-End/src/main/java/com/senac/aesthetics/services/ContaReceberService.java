package com.senac.aesthetics.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.ContaReceber;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.ErroGenerico;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.ContaReceberRepository;

@Service
public class ContaReceberService implements InterfaceGenericaResource<ContaReceber> {

    // Objetos:
    @Autowired
    private ContaReceberRepository contaReceberRepository;

    // Métodos:
    public Page<ContaReceber> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return contaReceberRepository.findAll(pagina);
    }

    public ContaReceber obterPorId(Long idContaReceber) throws Exception {
        Optional<ContaReceber> contaReceber = contaReceberRepository.findById(idContaReceber);

        if (contaReceber.isPresent()) {
            return contaReceber.get();
        } else {
            throw new ErroGenerico(
                    Arrays.asList("ContaReceber Não Encontrado! ID: " + idContaReceber),
                    TipoMensagemEnum.ERROR);
        }
    }

    public ContaReceber inserir(ContaReceber contaReceber) throws Exception {
        return contaReceberRepository.save(contaReceber);
    }

    public ContaReceber atualizar(ContaReceber contaReceber) throws Exception {
        if (contaReceberRepository.existsById(contaReceber.getId())) {
            return contaReceberRepository.saveAndFlush(contaReceber);
        } else {
            throw new ErroGenerico(
                    Arrays.asList("ContaReceber Não Encontrado! ID: " + contaReceber.getId()),
                    TipoMensagemEnum.ERROR);
        }
    }

    public void excluir(Long idContaReceber) throws Exception {
        if (contaReceberRepository.existsById(idContaReceber)) {
            contaReceberRepository.deleteById(idContaReceber);
        } else {
            throw new ErroGenerico(
                    Arrays.asList("ContaReceber Não Encontrado! ID: " + idContaReceber),
                    TipoMensagemEnum.ERROR);
        }
    }

}
