import { PessoaJuridica } from './abstracts/pessoa-juridica.abstract-model';

export class Fornecedor extends PessoaJuridica {
  // Atributo(s):
  public readonly id!: number;

  constructor() {
    super();
  }
}
