import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Funcionario } from 'src/app/shared/domains/funcionario.model';
import { Pessoa } from 'src/app/shared/domains/pessoa.model';
import { FuncionarioService } from 'src/app/shared/services/funcionario.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';
import { EstadosBrasileirosEnum } from 'src/app/shared/domains/enums/estados-brasileiros.enum';
@Component({
	selector: 'app-formulario-funcionarios',
	templateUrl: './formulario-funcionarios.component.html',
	styles: [],
})
export class FormularioFuncionariosComponent implements OnInit {
  exibirFormulario: Boolean = false;
  estadosBrasileiro!: string[];

	@Input() funcionario: Funcionario = new Funcionario();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(private funcionarioService: FuncionarioService, private mensagensGenericasService: MensagensGenericasService) {}
   
  ngOnInit(): void {
		this.estadosBrasileiro = Object.keys(EstadosBrasileirosEnum).map((value) => value);
	}

  salvarFuncionario() {
		if (this.funcionario.id) {
			this.atualizarFuncionario();
		} else {
			this.inserirFuncionario();
		}
	}

	atualizarFuncionario() {
		this.funcionarioService.atualizar(this.funcionario).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Funcionário', 'Atualizado');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	inserirFuncionario() {
		this.funcionarioService.inserir(this.funcionario).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Funcionário', 'Inserido');
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
