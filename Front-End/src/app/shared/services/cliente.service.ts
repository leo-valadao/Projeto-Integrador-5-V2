import { Injectable } from '@angular/core';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { Cliente } from '../domains/cliente.model';
import { HttpClient } from '@angular/common/http';
import { ServicoGenericoService } from './abstracts/servico-generico.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ClienteService
  extends ServicoGenericoService<Cliente>
  implements IntefaceGenericaComponentes<Cliente>
{
  override url = `${environment.CLIENTE}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
