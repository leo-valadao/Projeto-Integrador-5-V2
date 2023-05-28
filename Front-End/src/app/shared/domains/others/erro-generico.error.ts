import { HttpErrorResponse } from '@angular/common/http';
import { TipoMensagemEnum } from '../enums/tipo-mensagem.enum';

export class ErroGenerico {
	public mensagens!: string[];
	public tipoMensagem!: TipoMensagemEnum;

	constructor() {}
}
