import { Injectable } from '@angular/core';
import { IntefaceGenericaComponentes } from '../../interfaces/interface-generica-componentes.interface';
import { Observable } from 'rxjs';
import { Pagina } from '../../domains/others/pagina.page';
import { HttpClient } from '@angular/common/http';

@Injectable({
	providedIn: 'root',
})
export abstract class ServicoGenericoService<T> implements IntefaceGenericaComponentes<T> {
	url: string = '';

	constructor(protected http: HttpClient) {}

	obterTodosPorPagina(numeroPagina?: number, quantidadePorPagina?: number, ordenarPor?: string): Observable<Pagina<T>> {
		let url = `${this.url}?numeroPagina=${numeroPagina}&quantidadePorPagina=${quantidadePorPagina}`;

		if (ordenarPor) {
			url += `&ordenarPor=${ordenarPor}`;
		}

		return this.http.get<Pagina<T>>(url);
	}

	inserir(objeto: T): Observable<T> {
		let url = `${this.url}`;

		return this.http.post<T>(url, objeto);
	}

	atualizar(objeto: T): Observable<T> {
		let url = `${this.url}`;

		return this.http.put<T>(url, objeto);
	}

	excluir(idT: number): Observable<void> {
		let url = `${this.url}?id=${idT}`;

		return this.http.delete<void>(url);
	}
}
