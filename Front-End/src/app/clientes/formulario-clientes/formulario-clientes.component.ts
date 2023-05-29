import { Component, Input } from '@angular/core';
import { Cliente } from 'src/app/shared/domains/cliente.model';

@Component({
	selector: 'app-formulario-clientes',
	templateUrl: './formulario-clientes.component.html',
	styles: [],
})
export class FormularioClientesComponent {
	exibirFormulario: Boolean = false;

	@Input() cliente: Cliente = new Cliente();
}
