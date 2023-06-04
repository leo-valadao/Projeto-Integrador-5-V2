import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { Agendamento } from 'src/app/shared/domains/agendamento.model';
import { AgendamentoService } from 'src/app/shared/services/agendamento.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
	selector: 'app-tabela-agendamentos',
	templateUrl: './tabela-agendamentos.component.html',
	styles: [],
})
export class TabelaAgendamentosComponent {
	agendamentos!: Agendamento[];
	agendamentosSelecionados!: Agendamento[];
	quantidadeTotalAgendamentos!: number;
	quantidadeAgendamentosExibidosPorPagina: number = 30;

	colunas: { header: string; field: string }[] = [
		{ header: 'ID', field: 'id' },
		{ header: 'Data', field: 'data' },
		{ header: 'Horário', field: 'hora' },
		{ header: 'Status do Agendamento', field: 'status' },
		{ header: 'Obsrvação', field: 'observacao' },
		{ header: 'Cliente', field: 'cliente' },
		{ header: 'Funcionário', field: 'funcionario' },
		{ header: 'Serviço', field: 'servico' },
	];

	@Output() exibirFormularioAgendamento: EventEmitter<Agendamento> = new EventEmitter<Agendamento>();

	@ViewChild(Table) private tabelaAgendamentos!: Table;

	constructor(private agendamentoService: AgendamentoService, private mensagensGenericasService: MensagensGenericasService) {}

	ngOnInit(): void {
		this.obterTodosAgendamentos(0, 30);
	}

	obterTodosAgendamentos(numeroPagina: number, quantidadePorPagina: number, ordenarPor?: string): void {
		this.agendamentoService.obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor).subscribe({
			next: (resposta) => {
				this.agendamentos = resposta.content;
				this.quantidadeTotalAgendamentos = resposta.totalElements;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	mostrarFormularioAgendamentos(agendamento: Agendamento | null) {
		if (agendamento) {
			this.exibirFormularioAgendamento.emit(JSON.parse(JSON.stringify(agendamento)));
		} else {
			this.exibirFormularioAgendamento.emit(new Agendamento());
		}
	}

	excluirAgendamento(idAgendamento: number) {
		this.agendamentoService.excluir(idAgendamento).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Agendamentos', 'excluído');
				this.atualizarTabela();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabela() {
		this.obterTodosAgendamentos(Math.floor(Number(this.tabelaAgendamentos.first) / Number(this.tabelaAgendamentos.rows)), Number(this.tabelaAgendamentos._rows));
	}

	mudarPagina(evento: TableLazyLoadEvent) {
		if (evento.first != undefined && evento.rows != undefined) {
			this.obterTodosAgendamentos(Math.floor(evento.first / evento.rows), evento.rows, 'id');
		}
	}
}
