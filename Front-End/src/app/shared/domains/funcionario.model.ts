import { PessoaFisica } from './abstracts/pessoa-fisica.abstract-model';

export class Funcionario extends PessoaFisica {
  // Atributo(s):
  public id!: Number;
  public login!: String;
  public senha!: String;
  public comissao!: Number;

  constructor() {
    super();
  }
}
