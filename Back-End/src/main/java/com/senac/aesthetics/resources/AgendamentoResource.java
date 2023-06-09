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
import com.senac.aesthetics.interfaces.InterfaceGenericaCliente;
import com.senac.aesthetics.interfaces.InterfaceGenericaResource;
import com.senac.aesthetics.interfaces.InterfaceClienteObterAgendamentosSemOrdemServico;
import com.senac.aesthetics.interfaces.InterfaceResourceObterAgendamentosSemOrdemServico;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("api/v1/agendamento")
public class AgendamentoResource
        implements InterfaceGenericaCliente<Agendamento>, InterfaceClienteObterAgendamentosSemOrdemServico {

    // Obejtos:
    @Autowired
    private InterfaceGenericaResource<Agendamento> agendamentoService;

    @Autowired
    private InterfaceResourceObterAgendamentosSemOrdemServico agendamentoService2;

    // API's:
    @GetMapping
    public ResponseEntity<Page<Agendamento>> obterTodosComPaginacao(
            @RequestParam(name = "numeroPagina", defaultValue = "0") Integer numeroPagina,
            @RequestParam(name = "quantidadePorPagina", defaultValue = "25") Integer quantidadePorPagina,
            @RequestParam(name = "ordenarPor", defaultValue = "id") String ordernarPor) throws Exception {
        Page<Agendamento> agendamentos = agendamentoService.obterTodosComPaginacao(numeroPagina, quantidadePorPagina,
                ordernarPor);

        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping(params = "id")
    public ResponseEntity<Agendamento> obterPorId(@RequestParam(name = "id") Long id) throws Exception {
        Agendamento agendamento = agendamentoService.obterPorId(id);

        return ResponseEntity.ok(agendamento);
    }

    @GetMapping(value = "/sem-ordem-servico")
    public ResponseEntity<List<Agendamento>> obterAgendamentosSemOrdemServiço() throws Exception {
        List<Agendamento> agendamentos = agendamentoService2.obterAgendamentosSemOrdemServiço();

        return ResponseEntity.ok(agendamentos);
    }

    @PostMapping
    public ResponseEntity<Agendamento> inserir(@RequestBody @Valid Agendamento agendamento) throws Exception {
        Agendamento agendamentoInserido = agendamentoService.inserir(agendamento);

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

}
