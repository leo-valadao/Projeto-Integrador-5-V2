import { HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { TipoMensagemEnum } from '../enums/tipo-mensagem.enum';

export class ErroGenerico {
	public mensagem!: string;
	public tipoMensagem!: TipoMensagemEnum;
	public statusHTTP!: HttpStatusCode;
	public excecao!: Object;

	constructor() {}
}
