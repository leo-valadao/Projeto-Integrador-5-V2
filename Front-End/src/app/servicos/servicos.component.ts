import { Servico } from './../shared/domains/servico.model';
import { Component, ViewChild } from '@angular/core';
import { FormularioServicosComponent } from './formulario-servicos/formulario-servicos.component';
import { TabelaServicosComponent } from './tabela-servicos/tabela-servicos.component';

@Component({
	selector: 'app-servicos',
	templateUrl: './servicos.component.html',
	styles: [],
})
export class ServicosComponent {
	@ViewChild(FormularioServicosComponent) formularioServicos!: FormularioServicosComponent;
	@ViewChild(TabelaServicosComponent) tabelaServicos!: TabelaServicosComponent;

	exibirFormularioServico(servico: Servico) {
		this.formularioServicos.servico = JSON.parse(JSON.stringify(servico));
		this.formularioServicos.exibirFormulario = true;
	}

	atualizarTabela() {
		this.tabelaServicos.atualizarTabela();
	}
}
