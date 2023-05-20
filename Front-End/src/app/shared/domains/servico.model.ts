import { Agendamento } from './agendamento.model';
import { OrdemServico } from './ordem-servico.model';

export class Servico {
  // Atributo(s):
  public id!: Number;
  public nome!: String;
  public descricao!: String;
  public precoCusto!: Number;
  public precoVenda!: Number;

  // Relacionamento(s):
  public agendamentos!: Agendamento[];
  public ordensServicos!: OrdemServico[];

  constructor() {}
}
