import { Conta } from './abstracts/conta.abstract-model';
import { StatusContaPagarEnum } from './enums/status-conta-pagar.enum';
import { Fornecedor } from './fornecedor.model';

export class ContaPagar extends Conta {
  // Atributo(s):
  public id!: Number;
  public valorPago!: Number;
  public dataPagamento!: Date;
  public status!: StatusContaPagarEnum;

  // Relacionamento(s):
  public fornecedor!: Fornecedor;

  constructor() {
    super();
  }
}
