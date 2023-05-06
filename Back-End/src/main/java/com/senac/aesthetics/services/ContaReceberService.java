package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.ContaReceber;
import com.senac.aesthetics.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.services.interfaces.IContaReceberService;

@Service
public class ContaReceberService implements IContaReceberService {

    // Objetos:
    @Autowired
    private JpaRepository<ContaReceber, Long> contaReceberRepository;

    // Métodos:
    public Page<ContaReceber> obterTodasContasReceber(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return contaReceberRepository.findAll(pagina);
    }

    public ContaReceber obterContaReceberPorId(Long idContaReceber) {
        Optional<ContaReceber> contaReceber = contaReceberRepository.findById(idContaReceber);

        if (contaReceber.isPresent()) {
            return contaReceber.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "ContaReceber Não Encontrado! ID: " + idContaReceber);
        }
    }

    public ContaReceber inserirContaReceber(ContaReceber contaReceber) {
        return contaReceberRepository.save(contaReceber);
    }

    public ContaReceber atualizarContaReceber(ContaReceber contaReceber) {
        if (contaReceberRepository.existsById(contaReceber.getId())) {
            return contaReceberRepository.saveAndFlush(contaReceber);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR,
                    "ContaReceber Não Encontrado! ID: " + contaReceber.getId());
        }
    }

    public void excluirContaReceber(Long idContaReceber) {
        if (contaReceberRepository.existsById(idContaReceber)) {
            contaReceberRepository.deleteById(idContaReceber);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "ContaReceber Não Encontrado! ID: " + idContaReceber);
        }
    }

}
