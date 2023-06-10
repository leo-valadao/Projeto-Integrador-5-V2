import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { OrdemServico } from 'src/app/shared/domains/ordem-servico.model';
import { OrdemServicoService } from 'src/app/shared/services/ordem-servico.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
	selector: 'app-tabela-ordens-servicos',
	templateUrl: './tabela-ordens-servicos.component.html',
	styles: [],
})
export class TabelaOrdensServicosComponent {
	ordemServicos!: OrdemServico[];
	quantidadeTotalOrdemServicos!: number;
	quantidadeOrdemServicosExibidosPorPagina: number = 30;

	@Output() exibirFormularioOrdemServico: EventEmitter<OrdemServico> = new EventEmitter<OrdemServico>();

	@ViewChild(Table) private tabelaordemServicos!: Table;

	constructor(private ordemServicoService: OrdemServicoService, private mensagensGenericasService: MensagensGenericasService, private confirmationService: ConfirmationService) {}

	ngOnInit(): void {
		this.obterTodasOrdensServicos(0, 30);
	}

	obterTodasOrdensServicos(numeroPagina: number, quantidadePorPagina: number, ordenarPor?: string): void {
		this.ordemServicoService.obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor).subscribe({
			next: (resposta) => {
				this.ordemServicos = resposta.content;
				this.quantidadeTotalOrdemServicos = resposta.totalElements;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	mostrarFormularioOrdensServicos(ordemServico: OrdemServico | null) {
		if (ordemServico) {
			this.exibirFormularioOrdemServico.emit(ordemServico);
		} else {
			this.exibirFormularioOrdemServico.emit(new OrdemServico());
		}
	}

	verificarExclusao(idAgendamento: number) {
		this.confirmationService.confirm({
			message: 'Tem certeza que deseja excluir este ordem de serviço?',
			icon: 'pi pi-exclamation-triangle',
			accept: () => {
				this.excluirOrdemServico(idAgendamento);
			},
		});
	}

	private excluirOrdemServico(idOrdemServico: number) {
		this.ordemServicoService.excluir(idOrdemServico).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Ordem de Serviço', 'excluída');
				this.atualizarTabela();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabela() {
		this.obterTodasOrdensServicos(Math.floor(Number(this.tabelaordemServicos.first) / Number(this.tabelaordemServicos.rows)), Number(this.tabelaordemServicos._rows));
	}

	mudarPagina(evento: TableLazyLoadEvent) {
		if (evento.first != undefined && evento.rows != undefined) {
			this.obterTodasOrdensServicos(Math.floor(evento.first / evento.rows), evento.rows, 'id');
		}
	}
}
