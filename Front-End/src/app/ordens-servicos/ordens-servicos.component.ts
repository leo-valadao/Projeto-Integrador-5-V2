import { Component, ViewChild } from '@angular/core';
import { FormularioOrdensServicosComponent } from './formulario-ordens-servicos/formulario-ordens-servicos.component';
import { TabelaOrdensServicosComponent } from './tabela-ordens-servicos/tabela-ordens-servicos.component';
import { OrdemServico } from '../shared/domains/ordem-servico.model';

@Component({
	selector: 'app-ordens-servicos',
	templateUrl: './ordens-servicos.component.html',
	styles: [],
})
export class OrdensServicosComponent {
	@ViewChild(FormularioOrdensServicosComponent) formularioOrdensServicos!: FormularioOrdensServicosComponent;
	@ViewChild(TabelaOrdensServicosComponent) tabelaOrdensServicos!: TabelaOrdensServicosComponent;

	exibirFormularioOrdemServico(ordemServico: OrdemServico) {
		this.formularioOrdensServicos.ordemServico = JSON.parse(JSON.stringify(ordemServico));
		this.formularioOrdensServicos.exibirFormulario = true;
		this.formularioOrdensServicos.obterTodosAgendamentos();
	}

	atualizarTabela() {
		this.tabelaOrdensServicos.atualizarTabela();
	}
}
