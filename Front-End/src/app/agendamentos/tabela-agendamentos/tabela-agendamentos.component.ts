import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';
import { Agendamento } from 'src/app/shared/domains/agendamento.model';
import { AgendamentoService } from 'src/app/shared/services/agendamento.service';

@Component({
  selector: 'app-tabela-agendamentos',
  templateUrl: './tabela-agendamentos.component.html',
  styles: [],
})
export class TabelaAgendamentosComponent {
  agendamentos!: Agendamento[];
  agendamentosSelecionados!: Agendamento[];
  quantidadeTotalAgendamentos: Number = 10;
  quantidadeAgendamentosExibidosPorPagina: Number = 10;

  colunas: { header: string; field: string; align: string }[] = [
    { header: 'ID', field: 'id', align: 'text-center' },
    { header: 'Data', field: 'data', align: 'text-center' },
    { header: 'Horário', field: 'hora', align: 'text-center' },
    {
      header: 'Status do Agendamento',
      field: 'status',
      align: 'text-center',
    },
    {
      header: 'Obsrvação',
      field: 'observacao',
      align: 'text-center',
    },
    { header: 'Cliente', field: 'cliente', align: 'text-center' },
    { header: 'Funcionário', field: 'respAgendamento', align: 'text-center' },
    { header: 'Serviço', field: 'servico', align: 'text-center' },
  ];

  @Output() exibirFormularioAgendamento: EventEmitter<Agendamento> =
    new EventEmitter<Agendamento>();

  @ViewChild(Table) private tabelaAgendamentos!: Table;

  constructor(private agendamentoService: AgendamentoService) {}

  ngOnInit(): void {}

  obterTodosAgendamentos(
    numeroPagina: number,
    quantidadePorPagina: number,
    ordenarPor?: string
  ): void {
    this.agendamentoService
      .obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor)
      .subscribe({
        next: (resposta) => {
          this.agendamentos = resposta.content;
          this.quantidadeTotalAgendamentos = resposta.totalElements;
        },
        error: (erro) => {},
        complete: () => {},
      });
  }

  mudarPagina(evento: LazyLoadEvent) {
    if (evento.first != undefined && evento.rows != undefined) {
      this.obterTodosAgendamentos(
        Math.floor(evento.first / evento.rows),
        evento.rows,
        'id'
      );
    }
  }

  mostrarFormularioAgendamentos(cliente: Agendamento | null) {
    if (cliente) {
      this.exibirFormularioAgendamento.emit(
        JSON.parse(JSON.stringify(cliente))
      );
    } else {
      this.exibirFormularioAgendamento.emit(new Agendamento());
    }
  }

  excluirAgendamento(idAgendamento: number) {
    this.agendamentoService.excluir(idAgendamento).subscribe({
      next: (resposta) => {},
      error: (erro) => {},
      complete: () => {
      
      },
    });
 
  }





  
}
