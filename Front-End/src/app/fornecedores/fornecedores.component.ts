import { Fornecedor } from './../shared/domains/fornecedor.model';
import { Component, ViewChild } from '@angular/core';
import { TabelaFornecedoresComponent } from './tabela-fornecedores/tabela-fornecedores.component';
import { FormularioFornecedoresComponent } from './formulario-fornecedores/formulario-fornecedores.component';

@Component({
  selector: 'app-fornecedores',
  templateUrl: './fornecedores.component.html',
  styles: [],
})
export class FornecedoresComponent {
  @ViewChild(FormularioFornecedoresComponent) formularioFornecedores!: FormularioFornecedoresComponent;
	@ViewChild(TabelaFornecedoresComponent) tabelaFornecedores!: TabelaFornecedoresComponent;

	exibirFormularioFornecedores(fornecedor: Fornecedor) {
		this.formularioFornecedores.fornecedor = JSON.parse(JSON.stringify(fornecedor));
		this.formularioFornecedores.exibirFormulario = true;
	}

	atualizarTabela() {
		this.tabelaFornecedores.atualizarTabela();
	}

}
