import { EstadosBrasileirosEnum } from './enums/estados-brasileiros.enum';
import { TipoPessoaEnum } from './enums/tipo-pessoa.enum';

export class Pessoa {
	// Atributo(s):
	public readonly id!: number;
	public nome!: string;
	public telefone!: string;
	public email!: string;
	public estadoBrasileiro!: EstadosBrasileirosEnum;
	public tipoPessoa!: TipoPessoaEnum;
	public cpfOuCnpj!: string;

	constructor() {}
}
