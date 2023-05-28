package com.senac.aesthetics.services;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Funcionario;
import com.senac.aesthetics.domains.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.ErroGenerico;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.repositories.FuncionarioRepository;

@Service
public class FuncionarioService implements InterfaceGenericaResource<Funcionario> {

    // Objetos:
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Métodos:
    public Page<Funcionario> obterTodosComPaginacao(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) throws Exception {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return funcionarioRepository.findAll(pagina);
    }

    public Funcionario obterPorId(Long idFuncionario) throws Exception {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

        if (funcionario.isPresent()) {
            return funcionario.get();
        } else {
            throw new ErroGenerico(
                    Arrays.asList("Funcionario Não Encontrado! ID: " + idFuncionario),
                    TipoMensagemEnum.ERROR);
        }
    }

    public Funcionario inserir(Funcionario funcionario) throws Exception {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Funcionario funcionario) throws Exception {
        if (funcionarioRepository.existsById(funcionario.getId())) {
            return funcionarioRepository.saveAndFlush(funcionario);
        } else {
            throw new ErroGenerico(
                    Arrays.asList("Funcionario Não Encontrado! ID: " + funcionario.getId()),
                    TipoMensagemEnum.ERROR);
        }
    }

    public void excluir(Long idFuncionario) throws Exception {
        if (funcionarioRepository.existsById(idFuncionario)) {
            funcionarioRepository.deleteById(idFuncionario);
        } else {
            throw new ErroGenerico(
                    Arrays.asList("Funcionario Não Encontrado! ID: " + idFuncionario),
                    TipoMensagemEnum.ERROR);
        }
    }

}
