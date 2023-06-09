import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { Fornecedor } from 'src/app/shared/domains/fornecedor.model';
import { EstadosBrasileirosEnum } from 'src/app/shared/domains/enums/estados-brasileiros.enum';
import { Pessoa } from 'src/app/shared/domains/pessoa.model';
import { FornecedorService } from 'src/app/shared/services/fornecedor.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
	selector: 'app-tabela-fornecedores',
	templateUrl: './tabela-fornecedores.component.html',
	styles: [],
})
export class TabelaFornecedoresComponent implements OnInit {
	fornecedores!: Fornecedor[];
	FornecedoresSelecionados!: Fornecedor[];
	quantidadeTotalFornecedores!: number;
	quantidadeFornecedoresExibidosPorPagina: number = 30;

	@Output() exibirFormularioFornecedores: EventEmitter<Fornecedor> = new EventEmitter<Fornecedor>();

	@ViewChild(Table) private tabelaFornecedores!: Table;

	constructor(private fornecedorService: FornecedorService, private mensagensGenericasService: MensagensGenericasService) {}

	ngOnInit(): void {
		this.obterTodosFornecedores(0, 30);
	}

	obterTodosFornecedores(numeroPagina: number, quantidadePorPagina: number, ordenarPor?: string): void {
		this.fornecedorService.obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor).subscribe({
			next: (resposta) => {
				this.fornecedores = resposta.content;
				this.quantidadeTotalFornecedores = resposta.totalElements;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	mostrarFormularioFornecedores(fornecedor?: Fornecedor) {
		if (fornecedor) {
			this.exibirFormularioFornecedores.emit(fornecedor);
		} else {
			this.exibirFormularioFornecedores.emit(new Fornecedor());
		}
	}

	excluirFornecedor(idFornecedor: number) {
		this.fornecedorService.excluir(idFornecedor).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Fornecedor', 'excluído');
				this.atualizarTabela();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabela() {
		this.obterTodosFornecedores(Math.floor(Number(this.tabelaFornecedores.first) / Number(this.tabelaFornecedores.rows)), Number(this.tabelaFornecedores._rows));
	}

	mudarPagina(evento: TableLazyLoadEvent) {
		if (evento.first != undefined && evento.rows != undefined) {
			this.obterTodosFornecedores(Math.floor(evento.first / evento.rows), evento.rows, 'id');
		}
	}

	obterEstadoBrasileiro(valor: string) {
		return Object.values(EstadosBrasileirosEnum)[Object.keys(EstadosBrasileirosEnum).indexOf(valor)];
	}
}
