import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { Servico } from 'src/app/shared/domains/servico.model';
import { ServicoService } from 'src/app/shared/services/servico.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';
@Component({
	selector: 'app-tabela-servicos',
	templateUrl: './tabela-servicos.component.html',
	styles: [],
})
export class TabelaServicosComponent {
	servicos!: Servico[];
	quantidadeTotalServicos!: number;
	quantidadeServicosExibidosPorPagina: number = 30;

	@Output() exibirFormularioServico: EventEmitter<Servico> = new EventEmitter<Servico>();

	@ViewChild(Table) private tabelaServicos!: Table;

	constructor(private servicoService: ServicoService, private mensagensGenericasService: MensagensGenericasService, private confirmationService: ConfirmationService) {}

	ngOnInit(): void {
		this.obterTodosServicos(0, 30);
	}

	obterTodosServicos(numeroPagina: number, quantidadePorPagina: number, ordenarPor?: string): void {
		this.servicoService.obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor).subscribe({
			next: (resposta) => {
				this.servicos = resposta.content;
				this.quantidadeTotalServicos = resposta.totalElements;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	mostrarFormularioServicos(servico?: Servico) {
		if (servico) {
			this.exibirFormularioServico.emit(servico);
		} else {
			this.exibirFormularioServico.emit(new Servico());
		}
	}

	verificarExclusao(idAgendamento: number) {
		this.confirmationService.confirm({
			message: 'Tem certeza que deseja excluir este serviço?',
			icon: 'pi pi-exclamation-triangle',
			accept: () => {
				this.excluirServico(idAgendamento);
			},
		});
	}

	private excluirServico(idServico: number) {
		this.servicoService.excluir(idServico).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Serviço', 'excluído');
				this.atualizarTabela();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabela() {
		this.obterTodosServicos(Math.floor(Number(this.tabelaServicos.first) / Number(this.tabelaServicos.rows)), Number(this.tabelaServicos._rows));
	}

	mudarPagina(evento: TableLazyLoadEvent) {
		if (evento.first != undefined && evento.rows != undefined) {
			this.obterTodosServicos(Math.floor(evento.first / evento.rows), evento.rows, 'id');
		}
	}
}
