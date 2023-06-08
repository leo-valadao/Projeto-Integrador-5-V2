import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Fornecedor } from 'src/app/shared/domains/fornecedor.model';
import { EstadosBrasileirosEnum } from 'src/app/shared/domains/enums/estados-brasileiros.enum';
import { Pessoa } from 'src/app/shared/domains/pessoa.model';
import { FornecedorService } from 'src/app/shared/services/fornecedor.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
  selector: 'app-formulario-fornecedores',
  templateUrl: './formulario-fornecedores.component.html',
  styles: [],
})
export class FormularioFornecedoresComponent {

  exibirFormulario: Boolean = false;
	estadosBrasileiro!: string[];

	@Input() fornecedor: Fornecedor = new Fornecedor();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(private fonecedorService: FornecedorService, private mensagensGenericasService: MensagensGenericasService) {}

	ngOnInit(): void {
		this.estadosBrasileiro = Object.keys(EstadosBrasileirosEnum).map((value) => value);
	}

	salvarForncedor() {
		if (this.fornecedor.id) {
			this.atualizarFornecedor();
		} else {
			this.inserirFornecedor();
		}
	}

	atualizarFornecedor() {
		this.fonecedorService.atualizar(this.fornecedor).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Fornecedor', 'Atualizado');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	inserirFornecedor() {
		this.fonecedorService.inserir(this.fornecedor).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Fornecedor', 'Inserido');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
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

}
