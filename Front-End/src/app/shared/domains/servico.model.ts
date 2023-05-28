import { Agendamento } from './agendamento.model';
import { OrdemServico } from './ordem-servico.model';

export class Servico {
	// Atributo(s):
	public readonly id!: number;
	public nome!: string;
	public descricao!: string;
	public precoCusto!: number;
	public precoVenda!: number;

	// Relacionamento(s):
	public agendamentos!: Agendamento[];
	public ordensServicos!: OrdemServico[];

	constructor() {}
}
