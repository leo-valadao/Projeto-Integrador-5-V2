package com.senac.aesthetics.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.senac.aesthetics.domains.Funcionario;
import com.senac.aesthetics.enums.TipoMensagemEnum;
import com.senac.aesthetics.errors.DataBaseException;
import com.senac.aesthetics.interfaces.IGenericaService;

@Service
public class FuncionarioService implements IGenericaService<Funcionario> {

    // Objetos:
    @Autowired
    private JpaRepository<Funcionario, Long> funcionarioRepository;

    // Métodos:
    public Page<Funcionario> obterTodos(Integer numeroPagina, Integer quantidadePorPagina,
            String ordenarPor) {
        Pageable pagina = PageRequest.of(numeroPagina, quantidadePorPagina, Sort.by(Sort.Direction.DESC, ordenarPor));

        return funcionarioRepository.findAll(pagina);
    }

    public Funcionario obterPorId(Long idFuncionario) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(idFuncionario);

        if (funcionario.isPresent()) {
            return funcionario.get();
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Funcionario Não Encontrado! ID: " + idFuncionario);
        }
    }

    public Funcionario inserir(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Funcionario funcionario) {
        if (funcionarioRepository.existsById(funcionario.getId())) {
            return funcionarioRepository.saveAndFlush(funcionario);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR,
                    "Funcionario Não Encontrado! ID: " + funcionario.getId());
        }
    }

    public void excluir(Long idFuncionario) {
        if (funcionarioRepository.existsById(idFuncionario)) {
            funcionarioRepository.deleteById(idFuncionario);
        } else {
            throw new DataBaseException(TipoMensagemEnum.ERROR, "Funcionario Não Encontrado! ID: " + idFuncionario);
        }
    }

}