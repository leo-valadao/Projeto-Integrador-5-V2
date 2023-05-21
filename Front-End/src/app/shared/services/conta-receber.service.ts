import { Injectable } from '@angular/core';
import { ServicoGenericoService } from './abstracts/servico-generico.service';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { ContaReceber } from '../domains/conta-receber.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ContaReceberService
  extends ServicoGenericoService<ContaReceber>
  implements IntefaceGenericaComponentes<ContaReceber>
{
  override url = `${environment.CONTA_RECEBER}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
