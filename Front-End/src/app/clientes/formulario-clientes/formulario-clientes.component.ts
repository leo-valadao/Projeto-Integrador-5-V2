import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Cliente } from 'src/app/shared/domains/cliente.model';
import { EstadosBrasileirosEnum } from 'src/app/shared/domains/enums/estados-brasileiros.enum';
import { Pessoa } from 'src/app/shared/domains/pessoa.model';
import { ClienteService } from 'src/app/shared/services/cliente.service';
import { MensagensGenericasService } from 'src/app/shared/services/utils/mensagens-genericas.service';

@Component({
	selector: 'app-formulario-clientes',
	templateUrl: './formulario-clientes.component.html',
	styles: [],
})
export class FormularioClientesComponent implements OnInit {
	exibirFormulario: Boolean = false;
	estadosBrasileiro!: string[];

	@Input() cliente: Cliente = new Cliente(new Pessoa());
	@Output() atualizarTabela: EventEmitter<void> = new EventEmitter();

	constructor(private clienteService: ClienteService, private mensagensGenericasService: MensagensGenericasService) {}

	ngOnInit(): void {
		this.estadosBrasileiro = Object.keys(EstadosBrasileirosEnum).map((value) => value);
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
}
