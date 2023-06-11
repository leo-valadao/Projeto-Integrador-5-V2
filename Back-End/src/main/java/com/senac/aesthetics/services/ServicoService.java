package com.senac.aesthetics.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Servico;
import com.senac.aesthetics.domains.filters.ServicoFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.ServicoRepository;

@Service
public class ServicoService implements InterfaceGenericaResource<Servico, ServicoFiltro> {

    // Objetos:
    @Autowired
    private ServicoRepository servicoRepository;

    // Métodos:
    public Page<Servico> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor, ServicoFiltro filtro) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return servicoRepository.obterPorFiltroComPaginacao(filtro, pagina);
    }

    public Servico obterPorId(Long idServico) throws Exception {
        Optional<Servico> servico = servicoRepository.findById(idServico);

        if (servico.isPresent()) {
            return servico.get();
        } else {
            throw new NoSuchElementException("Servico Não Encontrado! ID: " + idServico);
        }
    }

    public Servico salvar(Servico servico) throws Exception {
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Servico servico) throws Exception {
        if (servicoRepository.existsById(servico.getId())) {
            return servicoRepository.saveAndFlush(servico);
        } else {
            throw new NoSuchElementException("Servico Não Encontrado! ID: " + servico.getId());
        }
    }

    public void excluir(Long idServico) throws Exception {
        if (servicoRepository.existsById(idServico)) {
            servicoRepository.deleteById(idServico);
        } else {
            throw new NoSuchElementException("Servico Não Encontrado! ID: " + idServico);
        }
    }

}
