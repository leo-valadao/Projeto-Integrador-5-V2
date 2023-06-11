import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { ContaPagar } from 'src/app/shared/domains/conta-pagar.model';
import { ContaPagarService } from 'src/app/shared/services/conta-pagar.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';
@Component({
	selector: 'app-tabela-contas-pagar',
	templateUrl: './tabela-contas-pagar.component.html',
	styles: [],
})
export class TabelaContasPagarComponent {
	contasPagar!: ContaPagar[];
	quantidadeTotalContasPagar!: number;
	quantidadeContasPagarExibidosPorPagina: number = 30;

	@Output() exibirFormularioContaPagar: EventEmitter<ContaPagar> = new EventEmitter<ContaPagar>();

	@ViewChild(Table) private tabelaContasPagar!: Table;

	constructor(private contaPagarService: ContaPagarService, private mensagensGenericasService: MensagensGenericasService, private confirmationService: ConfirmationService) {}

	ngOnInit(): void {
		this.obterTodosContasPagar(0, 30);
	}

	obterTodosContasPagar(numeroPagina: number, quantidadePorPagina: number, ordenarPor?: string): void {
		this.contaPagarService.obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor).subscribe({
			next: (resposta) => {
				this.contasPagar = resposta.content;
				this.quantidadeTotalContasPagar = resposta.totalElements;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	mostrarFormularioContasPagar(contaPagar?: ContaPagar) {
		if (contaPagar) {
			this.exibirFormularioContaPagar.emit(contaPagar);
		} else {
			this.exibirFormularioContaPagar.emit(new ContaPagar());
		}
	}

	verificarExclusao(idContaPagar: number) {
		this.confirmationService.confirm({
			message: 'Tem certeza que deseja excluir esta conta a pagar?',
			icon: 'pi pi-exclamation-triangle',
			accept: () => {
				this.excluirContaPagar(idContaPagar);
			},
		});
	}

	private excluirContaPagar(idContaPagar: number) {
		this.contaPagarService.excluir(idContaPagar).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Conta a pagar', 'excluída');
				this.atualizarTabela();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabela() {
		this.obterTodosContasPagar(Math.floor(Number(this.tabelaContasPagar.first) / Number(this.tabelaContasPagar.rows)), Number(this.tabelaContasPagar._rows));
	}

	mudarPagina(evento: TableLazyLoadEvent) {
		if (evento.first != undefined && evento.rows != undefined) {
			this.obterTodosContasPagar(Math.floor(evento.first / evento.rows), evento.rows, 'id');
		}
	}
}
