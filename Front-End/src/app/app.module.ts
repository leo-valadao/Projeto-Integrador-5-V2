// Angular
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

// Aesthetics
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PainelLateralComponent } from './painel-lateral/painel-lateral.component';
import { CabecalhoComponent } from './cabecalho/cabecalho.component';
import { AgendamentosComponent } from './agendamentos/agendamentos.component';
import { FormularioAgendamentosComponent } from './agendamentos/formulario-agendamentos/formulario-agendamentos.component';
import { FiltroAgendamentosComponent } from './agendamentos/filtro-agendamentos/filtro-agendamentos.component';
import { ClientesComponent } from './clientes/clientes.component';
import { FiltroClientesComponent } from './clientes/filtro-clientes/filtro-clientes.component';
import { TabelaClientesComponent } from './clientes/tabela-clientes/tabela-clientes.component';
import { FormularioClientesComponent } from './clientes/formulario-clientes/formulario-clientes.component';
import { ServicosComponent } from './servicos/servicos.component';
import { FiltroServicosComponent } from './servicos/filtro-servicos/filtro-servicos.component';
import { FormularioServicosComponent } from './servicos/formulario-servicos/formulario-servicos.component';
import { TabelaServicosComponent } from './servicos/tabela-servicos/tabela-servicos.component';
import { FuncionariosComponent } from './funcionarios/funcionarios.component';
import { FiltroFuncionariosComponent } from './funcionarios/filtro-funcionarios/filtro-funcionarios.component';
import { TabelaFuncionariosComponent } from './funcionarios/tabela-funcionarios/tabela-funcionarios.component';
import { FormularioFuncionariosComponent } from './funcionarios/formulario-funcionarios/formulario-funcionarios.component';
import { OrdensServicosComponent } from './ordens-servicos/ordens-servicos.component';
import { FiltroOrdensServicosComponent } from './ordens-servicos/filtro-ordens-servicos/filtro-ordens-servicos.component';
import { TabelaOrdensServicosComponent } from './ordens-servicos/tabela-ordens-servicos/tabela-ordens-servicos.component';
import { FormularioOrdensServicosComponent } from './ordens-servicos/formulario-ordens-servicos/formulario-ordens-servicos.component';
import { ContasReceberComponent } from './contas-receber/contas-receber.component';
import { FiltroContasReceberComponent } from './contas-receber/filtro-contas-receber/filtro-contas-receber.component';
import { TabelaContasReceberComponent } from './contas-receber/tabela-contas-receber/tabela-contas-receber.component';
import { FormularioContasReceberComponent } from './contas-receber/formulario-contas-receber/formulario-contas-receber.component';
import { ContasPagarComponent } from './contas-pagar/contas-pagar.component';
import { FormularioContasPagarComponent } from './contas-pagar/formulario-contas-pagar/formulario-contas-pagar.component';
import { FiltroContasPagarComponent } from './contas-pagar/filtro-contas-pagar/filtro-contas-pagar.component';
import { TabelaContasPagarComponent } from './contas-pagar/tabela-contas-pagar/tabela-contas-pagar.component';
import { RodapeComponent } from './rodape/rodape.component';
import { FornecedoresComponent } from './fornecedores/fornecedores.component';
import { FiltroFornecedoresComponent } from './fornecedores/filtro-fornecedores/filtro-fornecedores.component';
import { TabelaFornecedoresComponent } from './fornecedores/tabela-fornecedores/tabela-fornecedores.component';
import { FormularioFornecedoresComponent } from './fornecedores/formulario-fornecedores/formulario-fornecedores.component';
import { Rotas } from './app.routes';

@NgModule({
  declarations: [
    AppComponent,
    PainelLateralComponent,
    CabecalhoComponent,
    AgendamentosComponent,
    FormularioAgendamentosComponent,
    FiltroAgendamentosComponent,
    ClientesComponent,
    FiltroClientesComponent,
    TabelaClientesComponent,
    FormularioClientesComponent,
    ServicosComponent,
    FiltroServicosComponent,
    FormularioServicosComponent,
    TabelaServicosComponent,
    FuncionariosComponent,
    FiltroFuncionariosComponent,
    TabelaFuncionariosComponent,
    FormularioFuncionariosComponent,
    OrdensServicosComponent,
    FiltroOrdensServicosComponent,
    TabelaOrdensServicosComponent,
    FormularioOrdensServicosComponent,
    ContasReceberComponent,
    FiltroContasReceberComponent,
    TabelaContasReceberComponent,
    FormularioContasReceberComponent,
    ContasPagarComponent,
    FormularioContasPagarComponent,
    FiltroContasPagarComponent,
    TabelaContasPagarComponent,
    RodapeComponent,
    FornecedoresComponent,
    FiltroFornecedoresComponent,
    TabelaFornecedoresComponent,
    FormularioFornecedoresComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NoopAnimationsModule,
    RouterModule.forRoot(Rotas, { onSameUrlNavigation: 'reload' }),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
