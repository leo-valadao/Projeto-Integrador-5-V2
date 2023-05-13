package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.ContaPagar;
import com.senac.aesthetics.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.interfaces.IGenericaService;

@Service
public class ContaPagarService implements IGenericaService<ContaPagar> {

    // Objetos:
    @Autowired
    private JpaRepository<ContaPagar, Long> contaPagarRepository;

    // Métodos:
    public Page<ContaPagar> obterTodos(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return contaPagarRepository.findAll(pagina);
    }

    public ContaPagar obterPorId(Long idContaPagar) {
        Optional<ContaPagar> contaPagar = contaPagarRepository.findById(idContaPagar);

        if (contaPagar.isPresent()) {
            return contaPagar.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "ContaPagar Não Encontrado! ID: " + idContaPagar);
        }
    }

    public ContaPagar inserir(ContaPagar contaPagar) {
        return contaPagarRepository.save(contaPagar);
    }

    public ContaPagar atualizar(ContaPagar contaPagar) {
        if (contaPagarRepository.existsById(contaPagar.getId())) {
            return contaPagarRepository.saveAndFlush(contaPagar);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "ContaPagar Não Encontrado! ID: " + contaPagar.getId());
        }
    }

    public void excluir(Long idContaPagar) {
        if (contaPagarRepository.existsById(idContaPagar)) {
            contaPagarRepository.deleteById(idContaPagar);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "ContaPagar Não Encontrado! ID: " + idContaPagar);
        }
    }

}