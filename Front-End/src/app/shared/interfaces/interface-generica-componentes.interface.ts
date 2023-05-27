import { Observable } from 'rxjs';
import { Page } from '../domains/others/pagina.page';

export interface IntefaceGenericaComponentes<T> {
  // Atributo(s):
  url: String;

  // Método(s):
  obterTodosPorPagina(
    numeroDaPagina?: number,
    quantidadePorPagina?: number,
    ordenarPor?: string
  ): Observable<Page<T>>;

  inserir(objeto: T): Observable<T>;

  atualizar(objeto: T): Observable<T>;

  excluir(id: number): Observable<void>;
}
