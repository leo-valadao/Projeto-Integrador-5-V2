import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Servico } from '../domains/servico.model';
import { ServicoGenericoService } from './abstracts/servico-generico.service';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ServicoService
  extends ServicoGenericoService<Servico>
  implements IntefaceGenericaComponentes<Servico>
{
  override url = `${environment.SERVICO}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
