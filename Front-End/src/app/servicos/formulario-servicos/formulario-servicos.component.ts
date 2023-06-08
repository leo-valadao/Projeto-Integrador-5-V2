import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Pagina } from 'src/app/shared/domains/others/pagina.page';
import { Servico } from 'src/app/shared/domains/servico.model';
import { ServicoService } from 'src/app/shared/services/servico.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';
import { ValidacaoCamposService } from 'src/app/shared/services/utils/validacao-campos.service';

@Component({
	selector: 'app-formulario-servicos',
	templateUrl: './formulario-servicos.component.html',
	styles: [],
})
export class FormularioServicosComponent implements OnInit {
	exibirFormulario: Boolean = false;
	servicos!: Servico[];
	servicoSelecionado!: Servico;

	@Input() servico: Servico = new Servico();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(private servicoService: ServicoService, private mensagensGenericasService: MensagensGenericasService, public validacaoCamposService: ValidacaoCamposService) {}

	ngOnInit(): void {
		this.obterTodosServicos();
	}

	obterTodosServicos() {
		// TODO: Trocar consulta para uma mais apropriada, pois essa só retorna os 1000 últimos serviços registrados no banco de dados
		this.servicoService.obterTodosPorPagina(0, 1000).subscribe({
			next: (resposta: Pagina<Servico>) => {
				this.servicos = resposta.content;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

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

	servicoInvalido() {
		if (this.servico.nome && this.servico.precoCusto && this.servico.precoVenda) {
			return false;
		}
		return true;
	}
}
