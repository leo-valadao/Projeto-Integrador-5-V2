import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Dropdown } from 'primeng/dropdown';
import { ContaReceber } from 'src/app/shared/domains/conta-receber.model';
import { ContaReceberService } from 'src/app/shared/services/conta-receber.service';
import { Pagina } from 'src/app/shared/domains/others/pagina.page';
import { Cliente } from 'src/app/shared/domains/cliente.model';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { OrdemServico } from 'src/app/shared/domains/ordem-servico.model';
import { OrdemServicoService } from 'src/app/shared/services/ordem-servico.service';
import { ValidacaoCamposService } from 'src/app/shared/services/utils/validacao-campos.service';
import { StatusContaReceberEnum } from 'src/app/shared/domains/enums/status-conta-receber.enum';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
	selector: 'app-formulario-contas-receber',
	templateUrl: './formulario-contas-receber.component.html',
	styles: [],
})
export class FormularioContasReceberComponent implements OnInit {
	exibirFormulario: Boolean = false;
	ondernsServico!: OrdemServico[];
	ondernsServicoSelecionado!: OrdemServico;
	clientes!: Cliente[];
	clientesSelecionado!: Cliente;
	status!: string[];
	dataMinima: Date = new Date('01/01/' + (new Date().getFullYear() - 1).toString());

	@Input() contaReceber: ContaReceber = new ContaReceber();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(
		private contaReceberService: ContaReceberService,
		private clienteService: ClienteService,
		private ordemServicoService: OrdemServicoService,
		private mensagensGenericasService: MensagensGenericasService,
		public validacaoCamposService: ValidacaoCamposService
	) {}

	ngOnInit(): void {
		this.obterTodosClientes();
		this.obterTodasOrdensServico();
		// TODO: Melhorar a forma de como os valores das Enum's são exibidos na tela
		this.status = Object.keys(StatusContaReceberEnum).map((value) => value);
	}

	salvarContaReceber() {
		if (this.contaReceber.id) {
			this.atualizarContaReceber();
		} else {
			this.contaReceber.status = StatusContaReceberEnum.ABERTO;
			this.inserirContaReceber();
		}
	}

	atualizarContaReceber() {
		this.contaReceberService.atualizar(this.contaReceber).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Conta a receber', 'Atualizada');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	inserirContaReceber() {
		this.contaReceberService.inserir(this.contaReceber).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Conta a receber', 'Inserida');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	obterTodosClientes() {
		// TODO: Trocar consulta para uma mais apropriada, pois essa só retorna os 1000 últimos clientes registrados no banco de dados
		this.clienteService.obterTodosPorPagina(0, 1000).subscribe({
			next: (resposta: Pagina<Cliente>) => {
				this.clientes = resposta.content;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}
	obterTodasOrdensServico() {
		// TODO: Trocar consulta para uma mais apropriada, pois essa só retorna os 1000 últimos clientes registrados no banco de dados
		this.ordemServicoService.obterTodosPorPagina(0, 1000).subscribe({
			next: (resposta: Pagina<OrdemServico>) => {
				this.ondernsServico = resposta.content;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	atualizarTabelaEFecharFormulario() {
		this.atualizarTabela.emit();
		this.exibirFormulario = false;
	}

	contaReceberInvalido(): boolean {
		if (this.contaReceber.dataRecebimento && this.contaReceber.valorRecebido && this.contaReceber.ordemServico && this.contaReceber.cliente) {
			return false;
		}
		return true;
	}

	atualizarValorEValorAReceber() {
		this.contaReceber.valor = this.contaReceber.ordemServico.valor;
		this.contaReceber.valorRecebido = this.contaReceber.ordemServico.valor;
	}
}
