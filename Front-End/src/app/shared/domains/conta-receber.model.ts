import { Conta } from './abstracts/conta.abstract-model';
import { Cliente } from './cliente.model';
import { StatusContaPagarEnum } from './enums/status-conta-pagar.enum';
import { StatusContaReceberEnum } from './enums/status-conta-receber.enum';
import { OrdemServico } from './ordem-servico.model';

export class ContaReceber extends Conta {
  // Atributo(s):
  public readonly id!: number;
  public valorRecebido!: number;
  public recebimento!: Date;
  public status!: StatusContaReceberEnum;

  // Relacionamento(s):
  public ordemServico!: OrdemServico;
  public cliente!: Cliente;

  constructor() {
    super();
  }
}
