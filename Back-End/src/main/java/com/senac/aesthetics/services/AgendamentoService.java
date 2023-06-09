package com.senac.aesthetics.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Agendamento;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.interfaces.InterfaceResourceObterAgendamentosSemOrdemServico;
import com.senac.aesthetics.repositories.AgendamentoRepository;

@Service
public class AgendamentoService
        implements InterfaceGenericaResource<Agendamento>, InterfaceResourceObterAgendamentosSemOrdemServico {

    // Objetos:
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    // Métodos:
    public Page<Agendamento> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return agendamentoRepository.findAll(pagina);
    }

    public Agendamento obterPorId(Long idAgendamento) throws Exception {
        Optional<Agendamento> agendamento = agendamentoRepository.findById(idAgendamento);

        if (agendamento.isPresent()) {
            return agendamento.get();
        } else {
            throw new NoSuchElementException("Agendamento Não Encontrado! ID: " + idAgendamento);
        }
    }

    public List<Agendamento> obterAgendamentosSemOrdemServiço() throws Exception {
        return agendamentoRepository.obterAgendamentosSemOrdemServiço();
    }

    public Agendamento inserir(Agendamento agendamento) throws Exception {
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizar(Agendamento agendamento) throws Exception {
        if (agendamentoRepository.existsById(agendamento.getId())) {
            return agendamentoRepository.saveAndFlush(agendamento);
        } else {
            throw new NoSuchElementException("Agendamento Não Encontrado! ID: " + agendamento.getId());
        }
    }

    public void excluir(Long idAgendamento) throws Exception {
        if (agendamentoRepository.existsById(idAgendamento)) {
            agendamentoRepository.deleteById(idAgendamento);
        } else {
            throw new NoSuchElementException("Agendamento Não Encontrado! ID: " + idAgendamento);
        }
    }

}
