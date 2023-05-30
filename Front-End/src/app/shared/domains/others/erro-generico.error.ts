import { HttpErrorResponse } from '@angular/common/http';
import { TipoMensagemEnum } from '../enums/tipo-mensagem.enum';

export class ErroGenerico {
	public mensagem!: string;
	public tipoMensagem!: TipoMensagemEnum;

	constructor() {}
}
