import { Component, ViewChild } from '@angular/core';
import { TabelaAgendamentosComponent } from './tabela-agendamentos/tabela-agendamentos.component';
import { FormularioAgendamentosComponent } from './formulario-agendamentos/formulario-agendamentos.component';
import { Agendamento } from '../shared/domains/agendamento.model';

@Component({
	selector: 'app-agendamentos',
	templateUrl: './agendamentos.component.html',
	styles: [],
})
export class AgendamentosComponent {
	@ViewChild(FormularioAgendamentosComponent) formularioAgendamentos!: FormularioAgendamentosComponent;
	@ViewChild(TabelaAgendamentosComponent) tabelaAgendamentos!: TabelaAgendamentosComponent;

	exibirFormularioAgendamento(agendamento: Agendamento) {
		if (agendamento.id){
			this.formularioAgendamentos.agendamento = agendamento;
		}
		this.formularioAgendamentos.exibirFormulario = true;
	}

	atualizarTabela() {
		this.tabelaAgendamentos.atualizarTabela();
	}
}
