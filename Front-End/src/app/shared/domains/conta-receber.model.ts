import { Conta } from './abstracts/conta.abstract-model';
import { Cliente } from './cliente.model';
import { StatusContaPagarEnum } from './enums/status-conta-pagar.enum';
import { OrdemServico } from './ordem-servico.model';

export class ContaPagar extends Conta {
  // Atributo(s):
  public id!: Number;
  public valorRecebido!: Number;
  public dataRecebimento!: Date;
  public status!: StatusContaPagarEnum;

  // Relacionamento(s):
  public ordemServico!: OrdemServico;
  public cliente!: Cliente;

  constructor() {
    super();
  }
}
