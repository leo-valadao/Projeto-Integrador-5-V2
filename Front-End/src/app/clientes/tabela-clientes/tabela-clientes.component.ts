import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { Cliente } from 'src/app/shared/domains/cliente.model';
import { EstadosBrasileirosEnum } from 'src/app/shared/domains/enums/estados-brasileiros.enum';
import { Pessoa } from 'src/app/shared/domains/pessoa.model';
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

	mostrarFormularioClientes(cliente?: Cliente) {
		if (cliente) {
			this.exibirFormularioCliente.emit(cliente);
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

	obterEstadoBrasileiro(valor: string) {
		return Object.values(EstadosBrasileirosEnum)[Object.keys(EstadosBrasileirosEnum).indexOf(valor)];
	}
}
