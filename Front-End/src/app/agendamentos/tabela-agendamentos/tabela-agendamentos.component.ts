import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
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
	quantidadeTotalAgendamentos!: number;
	quantidadeAgendamentosExibidosPorPagina: number = 30;

	@Output() exibirFormularioAgendamento: EventEmitter<Agendamento> = new EventEmitter<Agendamento>();

	@ViewChild(Table) private tabelaAgendamentos!: Table;

	constructor(private agendamentoService: AgendamentoService, private mensagensGenericasService: MensagensGenericasService, private confirmationService: ConfirmationService) {}

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

	mostrarFormularioAgendamentos(agendamento?: Agendamento) {
		if (agendamento) {
			this.exibirFormularioAgendamento.emit(agendamento);
		} else {
			this.exibirFormularioAgendamento.emit(new Agendamento());
		}
	}

	verificarExclusao(idAgendamento: number) {
		this.confirmationService.confirm({
			message: 'Tem certeza que deseja excluir este agendamento?',
			icon: 'pi pi-exclamation-triangle',
			accept: () => {
				this.excluirAgendamento(idAgendamento);
			},
		});
	}

	private excluirAgendamento(idAgendamento: number) {
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
