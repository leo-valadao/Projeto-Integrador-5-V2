import { Injectable } from '@angular/core';
import { ServicoGenericoService } from './abstracts/servico-generico.service';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { ContaPagar } from '../domains/conta-pagar.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ContaPagarService
  extends ServicoGenericoService<ContaPagar>
  implements IntefaceGenericaComponentes<ContaPagar>
{
  override url = `${environment.CONTA_PAGAR}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
