import { Injectable } from "@angular/core";
import { MessageService } from "primeng/api";
import { ErroGenerico } from "../../domains/others/erro-generico.error";
import { TipoMensagemEnum } from "../../domains/enums/tipo-mensagem.enum";

@Injectable({
	providedIn: "root",
})
export class MensagensGenericasService {
	toastPrincipal: string = "toast";

	constructor(private messageService: MessageService) {}

	mensagemPadraoDeSucesso(classe: Object, verboOperacaoRealizada: string) {
		this.messageService.add({
			key: this.toastPrincipal,
			severity: TipoMensagemEnum.SUCCESS,
			summary: `${classe.constructor.name} ${verboOperacaoRealizada}!`,
			detail: `${classe.constructor.name} ${verboOperacaoRealizada.toLowerCase} com sucesso!`,
			life: 5000,
		});
	}

	mensagemPadraoDeErro(erro: ErroGenerico) {
		this.messageService.add({
			key: this.toastPrincipal,
			severity: erro.tipoMensagem,
			summary: `Erro!`,
			detail: `${erro.mensagem}`,
			life: 10000,
		});
	}
}
