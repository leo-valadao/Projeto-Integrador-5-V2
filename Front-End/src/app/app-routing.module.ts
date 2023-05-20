import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesComponent } from './clientes/clientes.component';
import { AgendamentosComponent } from './agendamentos/agendamentos.component';
import { ContasPagarComponent } from './contas-pagar/contas-pagar.component';
import { ContasReceberComponent } from './contas-receber/contas-receber.component';
import { FornecedoresComponent } from './fornecedores/fornecedores.component';
import { FuncionariosComponent } from './funcionarios/funcionarios.component';
import { OrdensServicosComponent } from './ordens-servicos/ordens-servicos.component';
import { ServicosComponent } from './servicos/servicos.component';

const routes: Routes = [
  {
    path: 'cliente',
    component: ClientesComponent,
  },
  {
    path: 'agendamento',
    component: AgendamentosComponent,
  },
  {
    path: 'servico',
    component: ServicosComponent,
  },
  {
    path: 'funcionario',
    component: FuncionariosComponent,
  },
  {
    path: 'ordem-servico',
    component: OrdensServicosComponent,
  },
  {
    path: 'conta-receber',
    component: ContasReceberComponent,
  },
  {
    path: 'conta-pagar',
    component: ContasPagarComponent,
  },
  {
    path: 'fornecedor',
    component: FornecedoresComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
