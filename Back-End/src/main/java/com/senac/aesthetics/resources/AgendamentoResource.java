package com.senac.aesthetics.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senac.aesthetics.domains.Agendamento;
import com.senac.aesthetics.domains.filters.AgendamentoFiltro;
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.interfaces.InterfaceResourceObterAgendamentosSemOrdemServico;
import com.senac.aesthetics.interfaces.InterfaceServiceObterAgendamentosSemOrdemServico;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/agendamento")
public class AgendamentoResource
        implements InterfaceGenericaCliente<Agendamento, AgendamentoFiltro>,
        InterfaceResourceObterAgendamentosSemOrdemServico {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<Agendamento, AgendamentoFiltro> agendamentoService;

    @Autowired
    private InterfaceServiceObterAgendamentosSemOrdemServico agendamentoService2;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Agendamento>> obterTodosComPaginacao(
            @RequestParam(required = false, name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(required = false, name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(required = false, name = "ordenarPor", defaultValue = "id") String ordernarPor,
            @RequestBody(required = false) AgendamentoFiltro filtro) throws Exception {
        Page<Agendamento> agendamentos = agendamentoService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor, filtro);

        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping
    public ResponseEntity<Agendamento> salvar(@RequestBody @Valid Agendamento agendamento) throws Exception {
        Agendamento agendamentoInserido = agendamentoService.salvar(agendamento);

        return ResponseEntity.created(null).body(agendamentoInserido);
    }

    @PutMapping
    public ResponseEntity<Agendamento> atualizar(@RequestBody @Valid Agendamento agendamento) throws Exception {
        Agendamento agendamentoAtualizado = agendamentoService.atualizar(agendamento);

        return ResponseEntity.ok(agendamentoAtualizado);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> excluir(@RequestParam(name = "id") Long id) throws Exception {
        agendamentoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/sem-ordem-servico")
    public ResponseEntity<List<Agendamento>> obterAgendamentosSemOrdemServiço() throws Exception {
        List<Agendamento> agendamentos = agendamentoService2.obterAgendamentosSemOrdemServiço();

        return ResponseEntity.ok(agendamentos);
    }

}
