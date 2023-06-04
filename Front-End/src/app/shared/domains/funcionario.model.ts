import { Pessoa } from './pessoa.model';

export class Funcionario {
	// Atributo(s):
	public readonly id!: number;
	public login!: string;
	public senha!: string;
	public comissao!: number;

	// Relacionamento(s):
	public pessoa!: Pessoa;

	constructor() {
		this.pessoa = new Pessoa();
	}
}
