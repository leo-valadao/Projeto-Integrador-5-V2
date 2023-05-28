import { EstadosBrasileirosEnum } from '../enums/estados-brasileiros.enum';

export abstract class Pessoa {
	// Atributo(s):
	public nome!: string;
	public telefone!: string;
	public email!: string;
	public estadoBrasileiro!: EstadosBrasileirosEnum;

	constructor() {}
}
