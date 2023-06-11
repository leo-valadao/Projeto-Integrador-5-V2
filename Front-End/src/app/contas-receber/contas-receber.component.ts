import { Component, ViewChild } from '@angular/core';
import { TabelaContasReceberComponent } from './tabela-contas-receber/tabela-contas-receber.component';
import { FormularioContasReceberComponent } from './formulario-contas-receber/formulario-contas-receber.component';
import { ContaReceber } from '../shared/domains/conta-receber.model';
import { FiltroContasReceberComponent } from './filtro-contas-receber/filtro-contas-receber.component';

@Component({
	selector: 'app-contas-receber',
	templateUrl: './contas-receber.component.html',
	styles: [],
})
export class ContasReceberComponent {
	@ViewChild(FormularioContasReceberComponent) formularioContasReceber!: FormularioContasReceberComponent;
	@ViewChild(TabelaContasReceberComponent) tabelaContasReceber!: TabelaContasReceberComponent;

	exibirFormularioContaReceber(contasReceber: ContaReceber) {
		this.formularioContasReceber.contaReceber = JSON.parse(JSON.stringify(contasReceber));
		this.formularioContasReceber.exibirFormulario = true;
	}

	atualizarTabela() {
		this.tabelaContasReceber.atualizarTabela();
	}
}
