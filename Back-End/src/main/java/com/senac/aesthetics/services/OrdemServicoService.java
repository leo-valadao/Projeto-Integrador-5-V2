package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.OrdemServico;
import com.senac.aesthetics.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.OrdemServicoRepository;

@Service
public class OrdemServicoService implements InterfaceGenericaResource<OrdemServico> {

    // Objetos:
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    // Métodos:
    public Page<OrdemServico> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return ordemServicoRepository.findAll(pagina);
    }

    public OrdemServico obterPorId(Long idOrdemServico) {
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(idOrdemServico);

        if (ordemServico.isPresent()) {
            return ordemServico.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "OrdemServico Não Encontrado! ID: " + idOrdemServico);
        }
    }

    public OrdemServico inserir(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public OrdemServico atualizar(OrdemServico ordemServico) {
        if (ordemServicoRepository.existsById(ordemServico.getId())) {
            return ordemServicoRepository.saveAndFlush(ordemServico);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR,
                    "OrdemServico Não Encontrado! ID: " + ordemServico.getId());
        }
    }

    public void excluir(Long idOrdemServico) {
        if (ordemServicoRepository.existsById(idOrdemServico)) {
            ordemServicoRepository.deleteById(idOrdemServico);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "OrdemServico Não Encontrado! ID: " + idOrdemServico);
        }
    }

}
