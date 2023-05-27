import { PessoaFisica } from './abstracts/pessoa-fisica.abstract-model';

export class Funcionario extends PessoaFisica {
  // Atributo(s):
  public readonly id!: number;
  public login!: String;
  public senha!: String;
  public comissao!: number;

  constructor() {
    super();
  }
}
