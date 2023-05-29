import { Component, ViewChild } from '@angular/core';
import { FormularioClientesComponent } from './formulario-clientes/formulario-clientes.component';
import { TabelaClientesComponent } from './tabela-clientes/tabela-clientes.component';
import { Cliente } from '../shared/domains/cliente.model';

@Component({
	selector: 'app-clientes',
	templateUrl: './clientes.component.html',
	styles: [],
})
export class ClientesComponent {
	@ViewChild(FormularioClientesComponent) formularioClientes!: FormularioClientesComponent;
	@ViewChild(TabelaClientesComponent) tabelaClientes!: TabelaClientesComponent;

	exibirFormularioCliente(cliente: Cliente) {
		if (cliente.id) {
			this.formularioClientes.cliente = cliente;
		}
		this.formularioClientes.exibirFormulario = true;
	}

	atualizarTabela() {
		this.tabelaClientes.atualizarTabela();
	}
}
