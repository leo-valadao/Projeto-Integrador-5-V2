import { PessoaFisica } from './abstracts/pessoa-fisica.abstract-model';

export class Cliente extends PessoaFisica {
  // Atributo(s):
  public readonly id!: Number;
  public alergias!: string;

  constructor() {
    super();
  }
}
