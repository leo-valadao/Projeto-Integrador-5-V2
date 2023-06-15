import uuid from "./uuid";
import { obterDataAmanha, obterHorarioAleatorio } from "./generateDate";

export const data = obterDataAmanha();
export const horario = obterHorarioAleatorio();

export function payloadAgendamento() {
  const payload = {
    data: data,
    hora: horario,
    status: "ABERTO",
    observacao: "Nenhum",
    cliente: { id: Cypress.env("cliId_agendamento") },
    funcionario: { id: Cypress.env("profId_agendamento") },
    servico: { id: Cypress.env("servfId_agendamento") },
  };

  return payload;
}

export function payloadAgendamentoPut() {
  const payload = {
    id: Cypress.env("agendamento_id"),
    data: data,
    hora: horario,
    status: "CONFIRMADO",
    observacao: "Observação editada",
    cliente: { id: Cypress.env("cliId_agendamento") },
    funcionario: { id: Cypress.env("profId_agendamento") },
    servico: { id: Cypress.env("servfId_agendamento") },
  };

  return payload;
}

export function payloadPostAgendamento() {
  const payload = {
    data: data,
    hora: horario,
    status: "ABERTO",
    observacao: "Nenhuma",
    cliente: { id: Cypress.env("cliId_agendamento") },
    funcionario: { id: Cypress.env("profId_agendamento") },
    servico: { id: Cypress.env("servfId_agendamento") },
  };

  return payload;
}

export function payloadPostServico() {
  const payload = {
    nome: "Corte de Cabelo e Escova",
    descricao: "Corte de Cabelo Feminino e Escova",
    precoCusto: 20.0,
    precoVenda: 100,
  };

  return payload;
}

export function payloadPutServico(servico_id) {
  const payload = {
    id: servico_id,
    nome: "Corte de Cabelo e Escova",
    descricao: "Corte de Cabelo Feminino com Escova",
    precoCusto: 25.0,
    precoVenda: 110,
  };

  return payload;
}

export function payloadPostServicoDel() {
  const payload = {
    nome: "Serviço para Exclusão",
    descricao: "Serviço para Exclusao",
    precoCusto: 20.0,
    precoVenda: 100,
  };

  return payload;
}

//#region Generate payload Clientes
export function payloadCliente() {
  const payload = {
    pessoa: {
      nome: "Amanda Isabela Costa",
      telefone: "(62)98610-6651",
      email: "amanda_isabela@ads.com.br",
      estadoBrasileiro: "GOIAS",
      tipoPessoa: "PESSOA_FISICA",
      cpfOuCnpj: "748.107.331-73",
    },
    alergias: "Nenhuma",
  };
  return payload;
}

export function payloadClienteExistente() {
  const payload = {
    pessoa: {
      nome: "Mariah Luiza Sueli Santos",
      telefone: "(62)99776-7614",
      email: "mariahluizasantos@ads.com.br",
      estadoBrasileiro: "GOIAS",
      tipoPessoa: "PESSOA_FISICA",
      cpfOuCnpj: "915.800.721-00",
    },
    alergias: "Nenhuma",
  };
  return payload;
}

export function payloadAlterarCliente(id) {
  const payload = {
    id: id,
    pessoa: {
      nome: "Eloá Carolina Luzia Cavalcanti",
      telefone: "(62)98315-8853",
      estadoBrasileiro: "GOIAS",
      tipoPessoa: "PESSOA_FISICA",
      cpfOuCnpj: "748.107.331-73",
      email: "eloa.carolina@ads.com",
    },
    alergias: "Shampoo de Camomila",
  };
  return payload;
}

export function payloadDelCliente() {
  const payload = {
    pessoa: {
      nome: "Valentina Teresinha da Costa",
      telefone: "(62)98875-9222",
      email: "valentina-dacosta81@ads.com",
      estadoBrasileiro: "GOIAS",
      tipoPessoa: "PESSOA_FISICA",
      cpfOuCnpj: "107.778.781-27",
    },
    alergias: "Nenhuma",
  };
  return payload;
}
//#endregion

//#region Generate payload Profissionais
export function payloadFuncionarioExistente() {
  const payload = {
    pessoa: {
      nome: "Nina Rayssa Santos",
      telefone: "(62)99458-2405",
      email: "nina.rayssa@ads.com.br",
      estadoBrasileiro: "GOIAS",
      tipoPessoa: "PESSOA_FISICA",
      cpfOuCnpj: "792.042.471-26",
    },
    login: "4sYDagS4H4",
    senha: "4sYDagS4H4",
    comissao: 10.0,
  };
  return payload;
}

export function payloadFuncionario() {
  const payload = {
    pessoa: {
      nome: "Anthony Antonio Davi Rodrigues",
      telefone: "(62)98806-4927",
      email: "anthony_antonio@ads.com.br",
      estadoBrasileiro: "GOIAS",
      tipoPessoa: "PESSOA_FISICA",
      cpfOuCnpj: "206.388.281-98",
    },
    login: "R6xgFzI9Sg",
    senha: "R6xgFzI9Sg",
    comissao: 10.0,
  };
  return payload;
}

export function payloadAlterarFuncionario(id) {
  const payload = {
    id: id,
    pessoa: {
      nome: "Anthony Antonio Davi",
      telefone: "(62)99732-1270",
      email: "anthony_davi@ads.com.br",
      estadoBrasileiro: "GOIAS",
      tipoPessoa: "PESSOA_FISICA",
      cpfOuCnpj: "206.388.281-98",
    },
    login: "Kptu2j5FNW",
    senha: "Kptu2j5FNW",
    comissao: 15.0,
  };
  return payload;
}

export function payloadDelFuncionario() {
  const payload = {
    pessoa: {
      nome: "Rosângela Isabella Silva",
      telefone: "(62)98733-9871",
      email: "rosangelaisabellasilva@ads.com.br",
      estadoBrasileiro: "GOIAS",
      tipoPessoa: "PESSOA_FISICA",
      cpfOuCnpj: "961.156.991-26",
    },
    login: "a5LcyyyyL3",
    senha: "a5LcyyyyL3",
    comissao: 15.0,
  };
  return payload;
}
//#endregion
