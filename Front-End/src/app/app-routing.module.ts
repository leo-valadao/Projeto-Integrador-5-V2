import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { AgendamentoComponent } from './agendamento/agendamento.component';
import { ContaPagarComponent } from './conta-pagar/conta-pagar.component';
import { ContaReceberComponent } from './conta-receber/conta-receber.component';
import { FornecedorComponent } from './fornecedor/fornecedor.component';
import { OrdemServicoComponent } from './ordem-servico/ordem-servico.component';
import { ServicoComponent } from './servico/servico.component';
import { FuncionarioComponent } from './funcionario/funcionario.component';


const routes: Routes = [
  {
    path: 'cliente',
    component: ClienteComponent,
  },
  {
    path: 'agendamento',
    component: AgendamentoComponent,
  },
  {
    path: 'conta-pagar',
    component: ContaPagarComponent,
  },
  {
    path: 'conta-receber',
    component: ContaReceberComponent,
  },
  {
    path: 'fornecedor',
    component: FornecedorComponent,
  },
  {
    path: 'funcionario',
    component: FuncionarioComponent,
  },
  {
    path: 'ordem-servico',
    component: OrdemServicoComponent,
  },
  {
    path: 'servico',
    component: ServicoComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
