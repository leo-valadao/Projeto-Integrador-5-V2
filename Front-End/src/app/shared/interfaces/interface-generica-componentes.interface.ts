import { Observable } from 'rxjs';
import { Page } from '../domains/others/page.model';

export interface IntefaceGenericaComponentes<T> {
  // Atributo(s):
  url: String;

  // Método(s):
  obterTodosPorPagina(
    numeroDaPagina?: Number,
    quantidadePorPagina?: Number,
    ordenarPor?: string
  ): Observable<Page<T>>;

  inserir(objeto: T): Observable<T>;

  atualizar(objeto: T): Observable<T>;

  excluir(id: Number): Observable<void>;
}
