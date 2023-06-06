import { Time } from '@angular/common';
import { StatusAgendamentoEnum } from './enums/status-agendamento.enum';
import { Cliente } from './cliente.model';
import { Funcionario } from './funcionario.model';
import { Servico } from './servico.model';

export class Agendamento {
	// Atributo(s):
	public readonly id!: number;
	public data!: Date;
	public hora!: Date;
	public status!: StatusAgendamentoEnum;
	public observacao!: string;

	// Relacionamento(s):
	public cliente!: Cliente;
	public funcionario!: Funcionario;
	public servico!: Servico;

	constructor() {}
}
