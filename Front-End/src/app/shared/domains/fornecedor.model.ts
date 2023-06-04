import { Pessoa } from './pessoa.model';

export class Fornecedor {
	// Atributo(s):
	public readonly id!: number;

	// Relacionamento(s):
	public pessoa!: Pessoa;

	constructor(pessoa?: Pessoa) {
		if (pessoa) {
			this.pessoa = pessoa;
		}
	}
}
