package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Fornecedor;
import com.senac.aesthetics.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.interfaces.IGenericaService;

@Service
public class FornecedorService implements IGenericaService<Fornecedor> {

    // Objetos:
    @Autowired
    private JpaRepository<Fornecedor, Long> fornecedorRepository;

    // Métodos:
    public Page<Fornecedor> obterTodos(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return fornecedorRepository.findAll(pagina);
    }

    public Fornecedor obterPorId(Long idFornecedor) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(idFornecedor);

        if (fornecedor.isPresent()) {
            return fornecedor.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Fornecedor Não Encontrado! ID: " + idFornecedor);
        }
    }

    public Fornecedor inserir(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Fornecedor fornecedor) {
        if (fornecedorRepository.existsById(fornecedor.getId())) {
            return fornecedorRepository.saveAndFlush(fornecedor);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Fornecedor Não Encontrado! ID: " + fornecedor.getId());
        }
    }

    public void excluir(Long idFornecedor) {
        if (fornecedorRepository.existsById(idFornecedor)) {
            fornecedorRepository.deleteById(idFornecedor);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Fornecedor Não Encontrado! ID: " + idFornecedor);
        }
    }

}
