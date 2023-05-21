const enum PortasEnum {
  PORTA = '8080',
}

const enum LinksEnum {
  URL = `http://localhost:${PortasEnum.PORTA}/api/`,
}

const enum VersaoEnum {
  V1 = 'v1',
}

const enum ApisEnum {
  AGENDAMENTO = 'agendamento',
  CLIENTE = 'cliente',
  CONTA_PAGAR = 'conta-pagar',
  CONTA_RECEBER = 'conta-receber',
  FORNECEDOR = 'fornecedor',
  FUNCIONARIO = 'funcionario',
  ORDEM_SERVICO = 'ordem-servico',
  SERVICO = 'servico',
}

export const enum environment {
  CLIENTE = `${LinksEnum.URL}${VersaoEnum.V1}${ApisEnum.CLIENTE}`,
  CONTA_PAGAR = `${LinksEnum.URL}${VersaoEnum.V1}${ApisEnum.CONTA_PAGAR}`,
  CONTA_RECEBER = `${LinksEnum.URL}${VersaoEnum.V1}${ApisEnum.CONTA_RECEBER}`,
  FORNECEDOR = `${LinksEnum.URL}${VersaoEnum.V1}${ApisEnum.FORNECEDOR}`,
  FUNCIONARIO = `${LinksEnum.URL}${VersaoEnum.V1}${ApisEnum.FUNCIONARIO}`,
  ORDEM_SERVICO = `${LinksEnum.URL}${VersaoEnum.V1}${ApisEnum.ORDEM_SERVICO}`,
  SERVICO = `${LinksEnum.URL}${VersaoEnum.V1}${ApisEnum.SERVICO}`,
}
