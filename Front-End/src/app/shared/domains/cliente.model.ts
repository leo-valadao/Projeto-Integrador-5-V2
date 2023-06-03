import { Pessoa } from './pessoa.model';

export class Cliente {
	// Atributo(s):
	public readonly id!: number;
	public alergias!: string;

	// Relacionamento(s):
	public pessoa!: Pessoa;

	constructor(pessoa?: Pessoa) {
		if (pessoa) {
			this.pessoa = pessoa;
		}
	}
}
