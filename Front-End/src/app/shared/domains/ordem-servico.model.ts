import { Agendamento } from './agendamento.model';
import { StatusOrdemServicoEnum } from './enums/status-ordem-servico.enum';
import { Funcionario } from './funcionario.model';
import { Servico } from './servico.model';

export class OrdemServico {
  // Atributo(s):
  public readonly id!: number;
  public dataHoraInicio!: Date;
  public dataHoraTermino!: Date;
  public status!: StatusOrdemServicoEnum;
  public valor!: number;

  // Relacionamento(s):
  public agendamento!: Agendamento;
  public servico!: Servico;
  public respOS!: Funcionario;
  public execServico!: Funcionario;

  constructor() {}
}
