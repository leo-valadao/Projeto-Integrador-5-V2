import { OrdemServicoService } from './../../shared/services/ordem-servico.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { OrdemServico } from 'src/app/shared/domains/ordem-servico.model';
import { StatusOrdemServicoEnum } from 'src/app/shared/domains/enums/status-ordem-servico.enum';
import { Pagina } from 'src/app/shared/domains/others/pagina.page';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';
import { Agendamento } from 'src/app/shared/domains/agendamento.model';
import { AgendamentoService } from 'src/app/shared/services/agendamento.service';
import { Funcionario } from 'src/app/shared/domains/funcionario.model';
import { FuncionarioService } from 'src/app/shared/services/funcionario.service';
import { Servico } from 'src/app/shared/domains/servico.model';
import { ServicoService } from 'src/app/shared/services/servico.service';
import { ValidacaoCamposService } from 'src/app/shared/services/utils/validacao-campos.service';
import { InputNumber } from 'primeng/inputnumber';
import { NgModel } from '@angular/forms';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-formulario-ordens-servicos',
  templateUrl: './formulario-ordens-servicos.component.html',
  styles: [],
})
export class FormularioOrdensServicosComponent implements OnInit {
	exibirFormulario: Boolean = false;
	funcionarios!: Funcionario[];
	agendamentos!: Agendamento[];
	servicos!: Servico[];
	status!: string[];
	dataMinima: Date = new Date('01/01/' + (new Date().getFullYear() - 1).toString());

	@Input() ordemServico: OrdemServico = new OrdemServico();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	@ViewChild('ordemServicoValor') valorOrdemServico!: NgModel;

	constructor(
		private agendamentoService: AgendamentoService,
		private funcionarioService: FuncionarioService,
		private servicoService: ServicoService,
		private ordemServicoService: OrdemServicoService,
		private mensagensGenericasService: MensagensGenericasService,
		public validacaoCamposService: ValidacaoCamposService
	) {}

	ngOnInit(): void {
		this.obterTodosAgendamentos();
		this.obterTodosServicos();
		this.obterTodosFuncionarios();
		this.status = Object.keys(StatusOrdemServicoEnum).map((value) => value);
	}

	salvarOrdemServico() {
		if (this.ordemServico.id) {
			this.atualizarOrdemServico();
		} else {
			this.ordemServico.status = StatusOrdemServicoEnum.ABERTO;
			this.inserirOrdemServico();
		}
	}

	atualizarOrdemServico() {
		this.ordemServicoService.atualizar(this.ordemServico).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Ordem de Serviço', 'Atualizado');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	inserirOrdemServico() {
		this.ordemServicoService.inserir(this.ordemServico).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Ordem de Serviço inserida', 'Inserida');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	obterTodosAgendamentos() {
		this.agendamentoService.obterAgendamentosSemOrdemServiço().subscribe({
			next: (resposta: Agendamento[]) => {
				this.agendamentos = resposta;
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
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

	obterTodosFuncionarios() {
		// TODO: Trocar consulta para uma mais apropriada, pois essa só retorna os 1000 últimos funcionários registrados no banco de dados
		this.funcionarioService.obterTodosPorPagina(0, 1000).subscribe({
			next: (resposta: Pagina<Funcionario>) => {
				this.funcionarios = resposta.content;
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

	definirValorOrdemServico() {
		this.ordemServico.valor = this.ordemServico.servico.precoVenda;
	}

	validarValorOrdemServico() {
		this.valorOrdemServico.control.markAsTouched();
		this.validacaoCamposService.campoInvalido(this.valorOrdemServico);
	}
}
