import { EstadosBrasileriosEnum } from '../enums/estados-brasileiros.enum';

export abstract class Pessoa {
	// Atributo(s):
	public nome!: String;
	public telefone!: String;
	public email!: String;
	public estadoBrasileiro!: EstadosBrasileriosEnum;

	constructor() {}
}
