import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { Funcionario } from 'src/app/shared/domains/funcionario.model';
import { Pessoa } from 'src/app/shared/domains/pessoa.model';
import { FuncionarioService } from 'src/app/shared/services/funcionario.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';
import { EstadosBrasileirosEnum } from 'src/app/shared/domains/enums/estados-brasileiros.enum';
import { ConfirmationService } from 'primeng/api';

@Component({
	selector: 'app-tabela-funcionarios',
	templateUrl: './tabela-funcionarios.component.html',
	styles: [],
})
export class TabelaFuncionariosComponent {
	funcionarios!: Funcionario[];
	funcionariosSelecionados!: Funcionario[];
	quantidadeTotalFuncionarios!: number;
	quantidadeFuncionariosExibidosPorPagina: number = 30;

	@Output() exibirFormularioFuncionario: EventEmitter<Funcionario> = new EventEmitter<Funcionario>();

	@ViewChild(Table) private tabelaFuncionarios!: Table;

	constructor(private funcionarioService: FuncionarioService, private mensagensGenericasService: MensagensGenericasService, private confirmationService: ConfirmationService) {}

	ngOnInit(): void {
		this.obterTodosFuncionarios(0, 30);
	}

	obterTodosFuncionarios(numeroPagina: number, quantidadePorPagina: number, ordenarPor?: string): void {
		this.funcionarioService.obterTodosPorPagina(numeroPagina, quantidadePorPagina, ordenarPor).subscribe({
			next: (resposta) => {
				this.funcionarios = resposta.content;
				this.quantidadeTotalFuncionarios = resposta.totalElements;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	mostrarFormularioFuncionarios(funcionario?: Funcionario) {
		if (funcionario) {
			this.exibirFormularioFuncionario.emit(funcionario);
		} else {
			this.exibirFormularioFuncionario.emit(new Funcionario());
		}
	}

	verificarExclusao(idAgendamento: number) {
		this.confirmationService.confirm({
			message: 'Tem certeza que deseja excluir este funcionário?',
			icon: 'pi pi-exclamation-triangle',
			accept: () => {
				this.excluirFuncionario(idAgendamento);
			},
		});
	}

	private excluirFuncionario(idFuncionario: number) {
		this.funcionarioService.excluir(idFuncionario).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Funcionário', 'excluído');
				this.atualizarTabela();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabela() {
		this.obterTodosFuncionarios(Math.floor(Number(this.tabelaFuncionarios.first) / Number(this.tabelaFuncionarios.rows)), Number(this.tabelaFuncionarios._rows));
	}

	mudarPagina(evento: TableLazyLoadEvent) {
		if (evento.first != undefined && evento.rows != undefined) {
			this.obterTodosFuncionarios(Math.floor(evento.first / evento.rows), evento.rows, 'id');
		}
	}

	obterEstadoBrasileiro(valor: string) {
		return Object.values(EstadosBrasileirosEnum)[Object.keys(EstadosBrasileirosEnum).indexOf(valor)];
	}
}
