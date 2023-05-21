import { Injectable } from '@angular/core';
import { Agendamento } from '../domains/agendamento.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { ServicoGenericoService } from './abstracts/servico-generico.service';

@Injectable({
  providedIn: 'root',
})
export class AgendamentoService
  extends ServicoGenericoService<Agendamento>
  implements IntefaceGenericaComponentes<Agendamento>
{
  override url: String = `${environment.AGENDAMENTO}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
