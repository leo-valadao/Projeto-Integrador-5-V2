import { Injectable } from '@angular/core';
import { IntefaceGenericaComponentes } from '../../interfaces/interface-generica-componentes.interface';
import { Observable } from 'rxjs';
import { Page } from '../../domains/others/page.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export abstract class ServicoGenericoService<T>
  implements IntefaceGenericaComponentes<T>
{
  url: String = '';

  constructor(protected http: HttpClient) {}

  obterTodosPorPagina(
    numeroPagina?: Number,
    quantidadePorPagina?: Number,
    ordenarPor?: string | undefined
  ): Observable<Page<T>> {
    let url = `${this.url}?numeroPagina=${numeroPagina}&quantidadePorPagina=${quantidadePorPagina}`;

    if (ordenarPor) {
      url += `&ordenarPor=${ordenarPor}`;
    }

    return this.http.get<Page<T>>(url);
  }

  inserir(objeto: T): Observable<T> {
    let url = `${this.url}`;

    return this.http.post<T>(url, objeto);
  }

  atualizar(objeto: T): Observable<T> {
    let url = `${this.url}`;

    return this.http.put<T>(url, objeto);
  }

  excluir(idT: Number): Observable<void> {
    let url = `?id=${idT}`;

    return this.http.delete<void>(url);
  }
}
