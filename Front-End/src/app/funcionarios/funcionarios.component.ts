import { Component, ViewChild } from '@angular/core';
import { TabelaFuncionariosComponent } from './tabela-funcionarios/tabela-funcionarios.component';
import { FormularioFuncionariosComponent } from './formulario-funcionarios/formulario-funcionarios.component';
import { Funcionario } from '../shared/domains/funcionario.model';

@Component({
	selector: 'app-funcionarios',
	templateUrl: './funcionarios.component.html',
	styles: [],
})
export class FuncionariosComponent {
	@ViewChild(FormularioFuncionariosComponent) formularioFuncionarios!: FormularioFuncionariosComponent;
	@ViewChild(TabelaFuncionariosComponent) tabelaFuncionarios!: TabelaFuncionariosComponent;

	exibirFormularioFuncionario(funcionario: Funcionario) {
		this.formularioFuncionarios.funcionario = JSON.parse(JSON.stringify(funcionario));
		this.formularioFuncionarios.exibirFormulario = true;
		this.formularioFuncionarios.modificarMascaraCpfOuCnpj();
	}

	atualizarTabela() {
		this.tabelaFuncionarios.atualizarTabela();
	}
}
