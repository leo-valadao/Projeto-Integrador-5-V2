import { Pessoa } from './pessoa.abstract-model';

export abstract class PessoaJuridica extends Pessoa {
	// Atributo(s):
	public cnpj!: string;

	constructor() {
		super();
	}
}
