
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClienteComponent } from './cliente/cliente.component';
import { PainelLateralComponent } from './painel-lateral/painel-lateral.component';
import { CabecalhoComponent } from './cabecalho/cabecalho.component';
import { AgendamentoComponent } from './agendamento/agendamento.component';
import { ServicoComponent } from './servico/servico.component';
import { FuncionarioComponent } from './funcionario/funcionario.component';
import { OrdemServicoComponent } from './ordem-servico/ordem-servico.component';
import { ContaPagarComponent } from './conta-pagar/conta-pagar.component';
import { ContaReceberComponent } from './conta-receber/conta-receber.component';
import { FornecedorComponent } from './fornecedor/fornecedor.component';

@NgModule({
  declarations: [
    AppComponent,
    ClienteComponent,
    PainelLateralComponent,
    CabecalhoComponent,
    AgendamentoComponent,
    ServicoComponent,
    FuncionarioComponent,
    OrdemServicoComponent,
    ContaPagarComponent,
    ContaReceberComponent,
    FornecedorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
