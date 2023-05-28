import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
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
	// Variáveis:
	exibirFormulario: Boolean = false;
	funcionarios!: Funcionario[];
	funcionarioSelecionado!: Funcionario;
	clientes!: Cliente[];
	clienteSelecionado!: Cliente;
	servicos!: Servico[];
	servicoSelecionado!: Servico;
	status!: SelectItem[];

	// Variáveis de Entrada(s) e Saída(s):
	@Input() agendamento: Agendamento = new Agendamento();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(
		private formBuilder: FormBuilder,
		private agendamentoService: AgendamentoService,
		private funcionarioService: FuncionarioService,
		private clienteService: ClienteService,
		private servicoService: ServicoService,
		private mensagensGenericasService: MensagensGenericasService
	) {}

	ngOnInit(): void {
		this.funcionarioService.obterTodosPorPagina(0, 1000).subscribe({
			next: (resposta: Pagina<Funcionario>) => {
				this.funcionarios = resposta.content;
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Funcionario', 'Carregados');
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});

		this.servicoService.obterTodosPorPagina(0, 1000).subscribe({
			next: (resposta: Pagina<Servico>) => {
				this.servicos = resposta.content;
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Servico', 'Carregados');
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});

		this.clienteService.obterTodosPorPagina(0, 1000).subscribe({
			next: (resposta: Pagina<Cliente>) => {
				this.clientes = resposta.content;
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Cliente', 'Carregados');
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});

		this.status = Object.keys(StatusAgendamentoEnum).map((value) => ({ label: value, value: value }));
	}

	salvarAgendamento() {
		this.agendamento.status = StatusAgendamentoEnum.ABERTO;

		if (this.agendamento.id) {
			this.atualizarAgendamento();
		} else {
			this.inserirAgendamento();
		}
	}

	atualizarAgendamento() {
		this.agendamentoService.atualizar(this.agendamento).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Agendamento', 'Atualizado');
				this.atualizarTabela.emit();
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
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}
}
