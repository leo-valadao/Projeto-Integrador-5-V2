import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ErroGenerico } from '../../domains/others/erro-generico.error';
import { TipoMensagemEnum } from '../../domains/enums/tipo-mensagem.enum';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
	providedIn: 'root',
})
export class MensagensGenericasService {
	toastPrincipal: string = 'toast';

	constructor(private messageService: MessageService) {}

	mensagemPadraoDeSucesso(nomeDaClasse: string, verboOperacaoRealizada: string) {
		this.messageService.add({
			key: this.toastPrincipal,
			severity: TipoMensagemEnum.SUCCESS,
			summary: `${nomeDaClasse} ${verboOperacaoRealizada}!`,
			detail: `${nomeDaClasse} ${verboOperacaoRealizada.toLowerCase()} com sucesso!`,
			life: 5000,
		});
	}

	mensagemPadraoDeErro(erro: HttpErrorResponse) {
		let erros: string[] = erro.error.mensagem.split('\n');

		for (let mensagem of erros) {
			this.messageService.add({
				key: this.toastPrincipal,
				severity: TipoMensagemEnum.ERROR,
				summary: `Erro!`,
				detail: `${mensagem.trim()}`,
				life: 10000,
			});
		}
	}
}
