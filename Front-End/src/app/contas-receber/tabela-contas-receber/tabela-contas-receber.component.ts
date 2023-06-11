import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { ContaReceber } from 'src/app/shared/domains/conta-receber.model';
import { ContaReceberService } from 'src/app/shared/services/conta-receber.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
	selector: 'app-tabela-contas-receber',
	templateUrl: './tabela-contas-receber.component.html',
	styles: [],
})
export class TabelaContasReceberComponent {
	contasReceber!: ContaReceber[];
	quantidadeTotalContasReceber!: number;
	quantidadeContasReceberExibidosPorPagina: number = 30;

	@Output() exibirFormularioContaReceber: EventEmitter<ContaReceber> = new EventEmitter<ContaReceber>();

	@ViewChild(Table) private tabelaContasReceber!: Table;

	constructor(private contaReceberService: ContaReceberService, private mensagensGenericasService: MensagensGenericasService, private confirmationService: ConfirmationService) {}

	ngOnInit(): void {
		this.obterTodosContasReceber(0, 30);
	}

	obterTodosContasReceber(numeroPagina: number, quantidadePorPagina: number, ordenarPor?: string): void {
		this.contaReceberService.obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor).subscribe({
			next: (resposta) => {
				this.contasReceber = resposta.content;
				this.quantidadeTotalContasReceber = resposta.totalElements;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	mostrarFormularioContasReceber(contaReceber?: ContaReceber) {
		if (contaReceber) {
			this.exibirFormularioContaReceber.emit(contaReceber);
		} else {
			this.exibirFormularioContaReceber.emit(new ContaReceber());
		}
	}

	verificarExclusao(idContaReceber: number) {
		this.confirmationService.confirm({
			message: 'Tem certeza que deseja excluir esta conta a receber?',
			icon: 'pi pi-exclamation-triangle',
			accept: () => {
				this.excluirContaPagar(idContaReceber);
			},
		});
	}

	private excluirContaPagar(idContaReceber: number) {
		this.contaReceberService.excluir(idContaReceber).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Conta a receber', 'excluída');
				this.atualizarTabela();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabela() {
		this.obterTodosContasReceber(Math.floor(Number(this.tabelaContasReceber.first) / Number(this.tabelaContasReceber.rows)), Number(this.tabelaContasReceber._rows));
	}

	mudarPagina(evento: TableLazyLoadEvent) {
		if (evento.first != undefined && evento.rows != undefined) {
			this.obterTodosContasReceber(Math.floor(evento.first / evento.rows), evento.rows, 'id');
		}
	}
}
