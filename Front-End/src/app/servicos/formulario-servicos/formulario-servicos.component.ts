import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Servico } from 'src/app/shared/domains/servico.model';
import { ServicoService } from 'src/app/shared/services/servico.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';


@Component({
  selector: 'app-formulario-servicos',
  templateUrl: './formulario-servicos.component.html',
  styles: [],
})
export class FormularioServicosComponent {
  exibirFormulario: Boolean = false;
	

	@Input() servico: Servico = new Servico();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(private servicoService: ServicoService, private mensagensGenericasService: MensagensGenericasService) {}



	salvarServico() {
		if (this.servico.id) {
			this.atualizarServico();
		} else {
			this.inserirServico();
		}
	}

	atualizarServico() {
		this.servicoService.atualizar(this.servico).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Serviço', 'Atualizado');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	inserirServico() {
		this.servicoService.inserir(this.servico).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Serviço', 'Inserido');
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
