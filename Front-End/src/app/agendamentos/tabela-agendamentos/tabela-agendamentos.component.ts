import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { Table, TableLazyLoadEvent } from 'primeng/table';
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
  quantidadeTotalAgendamentos: number = 10;
  quantidadeAgendamentosExibidosPorPagina: number = 10;

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

  constructor() {}

  ngOnInit(): void {}

}
