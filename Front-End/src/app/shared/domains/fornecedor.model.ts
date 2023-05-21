import { PessoaJuridica } from './abstracts/pessoa-juridica.abstract-model';

export class Fornecedor extends PessoaJuridica {
  // Atributo(s):
  public readonly id!: Number;

  constructor() {
    super();
  }
}
