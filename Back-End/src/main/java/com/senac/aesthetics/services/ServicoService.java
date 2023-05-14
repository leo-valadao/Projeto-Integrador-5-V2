package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Servico;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.ServicoRepository;

@Service
public class ServicoService implements InterfaceGenericaResource<Servico> {

    // Objetos:
    @Autowired
    private ServicoRepository servicoRepository;

    // Métodos:
    public Page<Servico> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return servicoRepository.findAll(pagina);
    }

    public Servico obterPorId(Long idServico) {
        Optional<Servico> servico = servicoRepository.findById(idServico);

        if (servico.isPresent()) {
            return servico.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Servico Não Encontrado! ID: " + idServico);
        }
    }

    public Servico inserir(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Servico servico) {
        if (servicoRepository.existsById(servico.getId())) {
            return servicoRepository.saveAndFlush(servico);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Servico Não Encontrado! ID: " + servico.getId());
        }
    }

    public void excluir(Long idServico) {
        if (servicoRepository.existsById(idServico)) {
            servicoRepository.deleteById(idServico);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Servico Não Encontrado! ID: " + idServico);
        }
    }

}
