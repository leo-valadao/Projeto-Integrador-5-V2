import { Routes } from '@angular/router';
import { ClientesComponent } from './clientes/clientes.component';
import { ServicosComponent } from './servicos/servicos.component';

import { AgendamentosComponent } from './agendamentos/agendamentos.component';
import { FuncionariosComponent } from './funcionarios/funcionarios.component';
import { ContasPagarComponent } from './contas-pagar/contas-pagar.component';
import { ContasReceberComponent } from './contas-receber/contas-receber.component';
import { FornecedoresComponent } from './fornecedores/fornecedores.component';
import { OrdensServicosComponent } from './ordens-servicos/ordens-servicos.component';

export const Rotas: Routes = [
  {
    path: 'agendamentos',
    component: AgendamentosComponent,
  },
  {
    path: 'clientes',
    component: ClientesComponent,
  },
  {
    path: 'contas-pagar',
    component: ContasPagarComponent,
  },
  {
    path: 'contas-receber',
    component: ContasReceberComponent,
  },
  {
    path: 'fornecedores',
    component: FornecedoresComponent,
  },
  {
    path: 'funcionarios',
    component: FuncionariosComponent,
  },
  {
    path: 'ordens-servicos',
    component: OrdensServicosComponent,
  },
  {
    path: 'servicos',
    component: ServicosComponent,
  },
];
