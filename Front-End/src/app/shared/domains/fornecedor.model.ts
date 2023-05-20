import { PessoaJuridica } from './abstracts/pessoa-juridica.abstract-model';

export class Fornecedor extends PessoaJuridica {
  // Atributo(s):
  public id!: Number;

  constructor() {
    super();
  }
}
