import { Component, ViewChild } from '@angular/core';
import { TabelaContasPagarComponent } from './tabela-contas-pagar/tabela-contas-pagar.component';
import { FormularioContasPagarComponent } from './formulario-contas-pagar/formulario-contas-pagar.component';
import { ContaPagar } from '../shared/domains/conta-pagar.model';
import { FiltroContasPagarComponent } from './filtro-contas-pagar/filtro-contas-pagar.component';
@Component({
	selector: 'app-contas-pagar',
	templateUrl: './contas-pagar.component.html',
	styles: [],
})
export class ContasPagarComponent {
	@ViewChild(FormularioContasPagarComponent) formularioContasPagar!: FormularioContasPagarComponent;
	@ViewChild(TabelaContasPagarComponent) tabelaContasPagar!: TabelaContasPagarComponent;

	exibirFormularioContaPagar(contasPagar: ContaPagar) {
		this.formularioContasPagar.contaPagar = JSON.parse(JSON.stringify(contasPagar));
		this.formularioContasPagar.exibirFormulario = true;
	}

	atualizarTabela() {
		this.tabelaContasPagar.atualizarTabela();
	}
}
