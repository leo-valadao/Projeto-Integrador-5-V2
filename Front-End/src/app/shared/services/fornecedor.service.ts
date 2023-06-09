import { Injectable } from '@angular/core';
import { ServicoGenericoService } from './abstracts/servico-generico.service';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Fornecedor } from '../domains/fornecedor.model';

@Injectable({
  providedIn: 'root',
})
export class FornecedorService
  extends ServicoGenericoService<Fornecedor>
  implements IntefaceGenericaComponentes<Fornecedor>
{
  override url = `${environment.FORNECEDOR}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
