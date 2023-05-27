import { Injectable } from '@angular/core';
import { ServicoGenericoService } from './abstracts/servico-generico.service';
import { Agendamento } from '../domains/agendamento.model';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AgendamentoService
  extends ServicoGenericoService<Agendamento>
  implements IntefaceGenericaComponentes<Agendamento>
{
  override url = `${environment.AGENDAMENTO}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
