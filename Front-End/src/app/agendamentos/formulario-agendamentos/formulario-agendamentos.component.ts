import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SelectItem } from 'primeng/api';
import { Agendamento } from 'src/app/shared/domains/agendamento.model';
import { Cliente } from 'src/app/shared/domains/cliente.model';
import { StatusAgendamentoEnum } from 'src/app/shared/domains/enums/status-agendamento.enum';
import { Funcionario } from 'src/app/shared/domains/funcionario.model';
import { Pagina } from 'src/app/shared/domains/others/pagina.page';
import { Servico } from 'src/app/shared/domains/servico.model';
import { AgendamentoService } from 'src/app/shared/services/agendamento.service';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { FuncionarioService } from 'src/app/shared/services/funcionario.service';
import { ServicoService } from 'src/app/shared/services/servico.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
	selector: 'app-formulario-agendamentos',
	templateUrl: './formulario-agendamentos.component.html',
	styles: [],
})
export class FormularioAgendamentosComponent implements OnInit {
	exibirFormulario: Boolean = false;
	funcionarios!: Funcionario[];
	funcionarioSelecionado!: Funcionario;
	clientes!: Cliente[];
	clienteSelecionado!: Cliente;
	servicos!: Servico[];
	servicoSelecionado!: Servico;
	status!: string[];

	@Input() agendamento: Agendamento = new Agendamento();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(
		private agendamentoService: AgendamentoService,
		private funcionarioService: FuncionarioService,
		private clienteService: ClienteService,
		private servicoService: ServicoService,
		private mensagensGenericasService: MensagensGenericasService
	) {}

	ngOnInit(): void {
		this.obterTodosClientes();
		this.obterTodosServicos();
		this.obterTodosFuncionarios();
		this.status = Object.keys(StatusAgendamentoEnum).map((value) => value);
	}

	salvarAgendamento() {
		if (this.agendamento.id) {
			this.atualizarAgendamento();
		} else {
			this.agendamento.status = StatusAgendamentoEnum.ABERTO;
			this.inserirAgendamento();
		}
	}

	atualizarAgendamento() {
		this.agendamentoService.atualizar(this.agendamento).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Agendamento', 'Atualizado');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	inserirAgendamento() {
		this.agendamentoService.inserir(this.agendamento).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Agendamento', 'Inserido');
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
}
