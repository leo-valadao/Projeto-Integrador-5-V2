import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { IntefaceGenericaComponentes } from '../interfaces/interface-generica-componentes.interface';
import { ServicoGenericoService } from './abstracts/servico-generico.service';
import { Funcionario } from '../domains/funcionario.model';

@Injectable({
  providedIn: 'root',
})
export class FuncionarioService
  extends ServicoGenericoService<Funcionario>
  implements IntefaceGenericaComponentes<Funcionario>
{
  override url = `${environment.FUNCIONARIO}`;

  constructor(protected override http: HttpClient) {
    super(http);
  }
}
