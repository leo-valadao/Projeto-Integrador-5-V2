package com.senac.aesthetics.resources.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.senac.aesthetics.domains.Agendamento;

import jakarta.validation.Valid;

public interface IAgendamentoResource {

    public ResponseEntity<Page<Agendamento>> obterTodosAgendamentos(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor);

    public ResponseEntity<Agendamento> obterAgendamentoPorId(@RequestParam(name = "id") Long id);

    public ResponseEntity<Agendamento> inserirAgendamento(@RequestBody @Valid Agendamento agendamento);

    public ResponseEntity<Agendamento> atualizarAgendamento(@RequestBody @Valid Agendamento agendamento);

    public ResponseEntity<Void> excluirAgendamento(@RequestParam(name = "id") Long id);

}
