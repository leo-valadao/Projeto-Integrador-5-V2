import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgModel } from '@angular/forms';
import { Cliente } from 'src/app/shared/domains/cliente.model';
import { EstadosBrasileirosEnum } from 'src/app/shared/domains/enums/estados-brasileiros.enum';
import { TipoPessoaEnum } from 'src/app/shared/domains/enums/tipo-pessoa.enum';
import { Pessoa } from 'src/app/shared/domains/pessoa.model';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';
import { ValidacaoCamposService } from 'src/app/shared/services/utils/validacao-campos.service';

@Component({
	selector: 'app-formulario-clientes',
	templateUrl: './formulario-clientes.component.html',
	styles: [],
})
export class FormularioClientesComponent implements OnInit {
	exibirFormulario: Boolean = false;
	estadosBrasileiro!: string[];
	tiposPessoas!: string[];
	mascaraCpfCnpj: string = '999.999.999-99';
	mascaraTelefone: string = '(99)99999-9999';

	@Input() cliente: Cliente = new Cliente();
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(private clienteService: ClienteService, private mensagensGenericasService: MensagensGenericasService, public validacaoCamposService: ValidacaoCamposService) {}

	ngOnInit(): void {
		this.estadosBrasileiro = Object.values(EstadosBrasileirosEnum).map((value) => value);
		this.tiposPessoas = Object.values(TipoPessoaEnum).map((value) => value);
	}

	salvarCliente() {
		if (this.cliente.id) {
			this.atualizarCliente();
		} else {
			this.inserirCliente();
		}
	}

	atualizarCliente() {
		this.clienteService.atualizar(this.cliente).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Cliente', 'Atualizado');
				this.atualizarTabela.emit();
				this.atualizarTabelaEFecharFormulario();
			},
			error: (erro: HttpErrorResponse) => {
				this.mensagensGenericasService.mensagemPadraoDeErro(erro);
			},
		});
	}

	inserirCliente() {
		this.clienteService.inserir(this.cliente).subscribe({
			next: () => {
				this.mensagensGenericasService.mensagemPadraoDeSucesso('Cliente', 'Inserido');
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

	modificarMascaraCpfOuCnpj() {
		if (this.cliente.pessoa.tipoPessoa == TipoPessoaEnum.PESSOA_FISICA || !this.cliente.pessoa.tipoPessoa) {
			this.mascaraCpfCnpj = '999.999.999-99';
		} else {
			this.mascaraCpfCnpj = '99.999.999/9999-99';
		}
	}

	clienteInvalido() {
		if (this.cliente.pessoa.nome && this.cliente.pessoa.tipoPessoa && this.cliente.pessoa.cpfOuCnpj && this.cliente.pessoa.telefone) {
			return false;
		}
		return true;
	}
}
