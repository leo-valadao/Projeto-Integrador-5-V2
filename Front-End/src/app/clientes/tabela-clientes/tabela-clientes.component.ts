import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { Cliente } from 'src/app/shared/domains/cliente.model';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
	selector: 'app-tabela-clientes',
	templateUrl: './tabela-clientes.component.html',
	styles: [],
})
export class TabelaClientesComponent {
	clientes!: Cliente[];
	clientesSelecionados!: Cliente[];
	quantidadeTotalClientes!: number;
	quantidadeClientesExibidosPorPagina: number = 30;

	colunas: { header: string; field: string }[] = [
		{ header: 'ID', field: 'id' },
		{ header: 'Nome', field: 'nome' },
		{ header: 'Telefone', field: 'telefone' },
		{ header: 'E-Mail', field: 'email' },
		{ header: 'Estado', field: 'estadoBrasileiro' },
		{ header: 'CPF', field: 'cpf' },
		{ header: 'Alergias', field: 'alergias' },
	];

	@Output() exibirFormularioCliente: EventEmitter<Cliente> = new EventEmitter<Cliente>();

	@ViewChild(Table) private tabelaClientes!: Table;

	constructor(private clienteService: ClienteService, private mensagensGenericasService: MensagensGenericasService) {}

	ngOnInit(): void {
		this.obterTodosClientes(0, 30);
	}

	obterTodosClientes(numeroPagina: number, quantidadePorPagina: number, ordenarPor?: string): void {
		this.clienteService.obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor).subscribe({
			next: (resposta) => {
				this.clientes = resposta.content;
				this.quantidadeTotalClientes = resposta.totalElements;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	mostrarFormularioClientes(cliente: Cliente | null) {
		if (cliente) {
			this.exibirFormularioCliente.emit(JSON.parse(JSON.stringify(cliente)));
		} else {
			this.exibirFormularioCliente.emit(new Cliente());
		}
	}

	excluirCliente(idCliente: number) {
		this.clienteService.excluir(idCliente).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Clientes', 'excluído');
				this.atualizarTabela();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabela() {
		this.obterTodosClientes(Math.floor(Number(this.tabelaClientes.first) / Number(this.tabelaClientes.rows)), Number(this.tabelaClientes._rows));
	}

	mudarPagina(evento: TableLazyLoadEvent) {
		if (evento.first != undefined && evento.rows != undefined) {
			this.obterTodosClientes(Math.floor(evento.first / evento.rows), evento.rows, 'id');
		}
	}
}
