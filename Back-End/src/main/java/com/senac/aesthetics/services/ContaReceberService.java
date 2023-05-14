package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.ContaReceber;
import com.senac.aesthetics.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.interfaces.IGenericaService;
import com.senac.aesthetics.repositories.ContaReceberRepository;

@Service
public class ContaReceberService implements IGenericaService<ContaReceber> {

    // Objetos:
    @Autowired
    private ContaReceberRepository contaReceberRepository;

    // Métodos:
    public Page<ContaReceber> obterTodos(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return contaReceberRepository.findAll(pagina);
    }

    public ContaReceber obterPorId(Long idContaReceber) {
        Optional<ContaReceber> contaReceber = contaReceberRepository.findById(idContaReceber);

        if (contaReceber.isPresent()) {
            return contaReceber.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "ContaReceber Não Encontrado! ID: " + idContaReceber);
        }
    }

    public ContaReceber inserir(ContaReceber contaReceber) {
        return contaReceberRepository.save(contaReceber);
    }

    public ContaReceber atualizar(ContaReceber contaReceber) {
        if (contaReceberRepository.existsById(contaReceber.getId())) {
            return contaReceberRepository.saveAndFlush(contaReceber);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR,
                    "ContaReceber Não Encontrado! ID: " + contaReceber.getId());
        }
    }

    public void excluir(Long idContaReceber) {
        if (contaReceberRepository.existsById(idContaReceber)) {
            contaReceberRepository.deleteById(idContaReceber);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "ContaReceber Não Encontrado! ID: " + idContaReceber);
        }
    }

}
