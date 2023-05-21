import { Agendamento } from './agendamento.model';
import { StatusOrdemServicoEnum } from './enums/status-ordem-servico.enum';
import { Funcionario } from './funcionario.model';
import { Servico } from './servico.model';

export class OrdemServico {
  // Atributo(s):
  public readonly id!: Number;
  public dataHoraInicio!: Date;
  public dataHoraTermino!: Date;
  public status!: StatusOrdemServicoEnum;
  public valor!: Number;

  // Relacionamento(s):
  public agendamento!: Agendamento;
  public servico!: Servico;
  public respOS!: Funcionario;
  public execServico!: Funcionario;

  constructor() {}
}
