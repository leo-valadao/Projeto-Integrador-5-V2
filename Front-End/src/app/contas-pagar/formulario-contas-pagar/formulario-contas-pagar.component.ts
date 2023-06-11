import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Dropdown } from 'primeng/dropdown';
import { ContaPagarService } from 'src/app/shared/services/conta-pagar.service';
import { ContaPagar } from 'src/app/shared/domains/conta-pagar.model';
import { Fornecedor } from 'src/app/shared/domains/fornecedor.model';
import { FornecedorService } from 'src/app/shared/services/fornecedor.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';
import { ValidacaoCamposService } from 'src/app/shared/services/utils/validacao-campos.service';
import { StatusContaPagarEnum } from 'src/app/shared/domains/enums/status-conta-pagar.enum';
import { Pagina } from 'src/app/shared/domains/others/pagina.page';

@Component({
  selector: 'app-formulario-contas-pagar',
  templateUrl: './formulario-contas-pagar.component.html',
  styles: [],
})
export class FormularioContasPagarComponent implements OnInit{
  exibirFormulario: Boolean = false;
	fornecedores!: Fornecedor[];
	fornecedoresSelecionado!: Fornecedor;
	status!: string[];
	dataMinima: Date = new Date('01/01/' + (new Date().getFullYear() - 1).toString());

	@Input() contaPagar: ContaPagar = new ContaPagar();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(
		private contaPagarService: ContaPagarService,
		private fornecedorSevice: FornecedorService,
		private mensagensGenericasService: MensagensGenericasService,
		public validacaoCamposService: ValidacaoCamposService
	) {}

	ngOnInit(): void {
		this.obterTodosFornecedores();
		// TODO: Melhorar a forma de como os valores das Enum's são exibidos na tela
		this.status = Object.keys(StatusContaPagarEnum).map((value) => value);
	}


  salvarContaPagar() {
		if (this.contaPagar.id) {
			this.atualizarContaPagar();
		} else {
			this.contaPagar.status = StatusContaPagarEnum.ABERTO;
			this.inserirContaPagar();
		}
	}

	atualizarContaPagar() {
		this.contaPagarService.atualizar(this.contaPagar).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Conta a pagar', 'Atualizada');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	inserirContaPagar() {
		this.contaPagarService.inserir(this.contaPagar).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Conta a pagar', 'Inserida');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	obterTodosFornecedores() {
		// TODO: Trocar consulta para uma mais apropriada, pois essa só retorna os 1000 últimos clientes registrados no banco de dados
		this.fornecedorSevice.obterTodosPorPagina(0, 1000).subscribe({
			next: (resposta: Pagina<Fornecedor>) => {
				this.fornecedores = resposta.content;
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

	contaPagarInvalido(): boolean {
		if (this.contaPagar.dataPagamento && this.contaPagar.valorPago && this.contaPagar.fornecedor ) {
			return false;
		}
		return true;
	}



}
