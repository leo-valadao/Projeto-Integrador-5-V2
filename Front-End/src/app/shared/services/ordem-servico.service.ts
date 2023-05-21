import { Injectable } from '@angular/core';
import { ServicoGenericoService } from './abstracts/servico-generico.service';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { OrdemServico } from '../domains/ordem-servico.model';

@Injectable({
  providedIn: 'root',
})
export class OrdemServicoService
  extends ServicoGenericoService<OrdemServico>
  implements IntefaceGenericaComponentes<OrdemServico>
{
  override url = `${environment.CLIENTE}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
