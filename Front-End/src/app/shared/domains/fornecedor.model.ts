import { Pessoa } from './pessoa.model';

export class Fornecedor {
	// Atributo(s):
	public readonly id!: number;
	public endereco!: string;

	// Relacionamento(s):
	public pessoa!: Pessoa;

	constructor() {
		this.pessoa = new Pessoa();
	}
}
