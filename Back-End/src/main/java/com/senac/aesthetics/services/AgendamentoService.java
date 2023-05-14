package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Agendamento;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.AgendamentoRepository;

@Service
public class AgendamentoService implements InterfaceGenericaResource<Agendamento> {

    // Objetos:
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // Métodos:
    public Page<Agendamento> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return agendamentoRepository.findAll(pagina);
    }

    public Agendamento obterPorId(Long idAgendamento) {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(idAgendamento);

        if (agendamento.isPresent()) {
            return agendamento.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Agendamento Não Encontrado! ID: " + idAgendamento);
        }
    }

    public Agendamento inserir(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizar(Agendamento agendamento) {
        if (agendamentoRepository.existsById(agendamento.getId())) {
            return agendamentoRepository.saveAndFlush(agendamento);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR,
                    "Agendamento Não Encontrado! ID: " + agendamento.getId());
        }
    }

    public void excluir(Long idAgendamento) {
        if (agendamentoRepository.existsById(idAgendamento)) {
            agendamentoRepository.deleteById(idAgendamento);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Agendamento Não Encontrado! ID: " + idAgendamento);
        }
    }

}
