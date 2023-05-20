import { Pessoa } from './pessoa.abstract-model';

export abstract class PessoaFisica extends Pessoa {
  // Atributo(s):
  public cpf!: String;

  constructor() {
    super();
  }
}
