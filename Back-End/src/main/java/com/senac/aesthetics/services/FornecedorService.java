package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Fornecedor;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.ErroGenerico;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.FornecedorRepository;

@Service
public class FornecedorService implements InterfaceGenericaResource<Fornecedor> {

    // Objetos:
    @Autowired
    private FornecedorRepository fornecedorRepository;

    // Métodos:
    public Page<Fornecedor> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return fornecedorRepository.findAll(pagina);
    }

    public Fornecedor obterPorId(Long idFornecedor) throws Exception {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent()) {
            return fornecedor.get();
        } else {
            throw new ErroGenerico("Fornecedor Não Encontrado! ID: " + idFornecedor,
                    TipoMensagemEnum.ERROR);
        }
    }

    public Fornecedor inserir(Fornecedor fornecedor) throws Exception {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Fornecedor fornecedor) throws Exception {
        if (fornecedorRepository.existsById(fornecedor.getId())) {
            return fornecedorRepository.saveAndFlush(fornecedor);
        } else {
            throw new ErroGenerico("Fornecedor Não Encontrado! ID: " + fornecedor.getId(),
                    TipoMensagemEnum.ERROR);
        }
    }

    public void excluir(Long idFornecedor) throws Exception {
        if (fornecedorRepository.existsById(idFornecedor)) {
            fornecedorRepository.deleteById(idFornecedor);
        } else {
            throw new ErroGenerico("Fornecedor Não Encontrado! ID: " + idFornecedor,
                    TipoMensagemEnum.ERROR);
        }
    }

}
