import { TipoMensagemEnum } from '../enums/tipo-mensagem.enum';

export class ErroGenerico {
  public mensagem!: String;
  public tipoMensagem!: TipoMensagemEnum;

  constructor() {}
}
