import { Observable } from 'rxjs';
import { Agendamento } from '../domains/agendamento.model';

export interface InterfaceServiceObterAgendamentosSemOrdemServico {
	// Método(s):
	obterAgendamentosSemOrdemServiço(): Observable<Agendamento[]>;
}
