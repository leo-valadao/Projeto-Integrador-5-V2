import uuid from "./uuid";

export function payloadPostAgendamento(
  cliente_id,
  profissional_id,
  servico_id
) {
  const data = "29/04/2023";
  const payload = {
    agendamentoDataHora: `${data} 15:50:12`,
    duracao: "02:00:00",
    finalizacaoAgendamento: `${data} 17:50:12`,
    cliente: { id: cliente_id },
    profissional: { id: profissional_id },
    servico: { id: servico_id },
  };

  return payload;
}

export function payloadPostCliente() {
  const payload = {
    nome: Cypress.env("nome"),
    telefone: Cypress.env("telefone").replace(/\s/g, ""),
    email: Cypress.env("email"),
    uf: "GO",
    cpf: Cypress.env("cpf"),
    alergias: "Nenhuma",
  };
  //.replace(/\s/g, "")

  return payload;
}

export function payloadPutCliente(id) {
  const payload = {
    id: id,
    nome: Cypress.env("nomePut"),
    cpf: Cypress.env("cpfPut"),
    telefone: Cypress.env("telefonePut").replace(/\s/g, ""),
    uf: "GO",
    email: Cypress.env("emailPut"),
    alergias: 'Shampoo de Camomila'
  };

  return payload;
}

export function payloadPostProfissional() {
  const payload = {
    nome: Cypress.env("profissional_nome"),
    telefone: Cypress.env("profissional_celular").replace(/\s/g, ""),
    email: Cypress.env("profissional_email"),
    uf: Cypress.env('profissional_uf'),
    cpf: Cypress.env("profissional_cpf"),
    login: Cypress.env("profissional_senha"),
    senha: Cypress.env("profissional_senha"),
    comissao: 12.50
  };

  return payload;
}

export function payloadPutProfissional() {
  const payload = {
    id: Cypress.env("profissional_id"),
    nome: Cypress.env("profissional_nome"),
    cpf: Cypress.env("profissional_cpf"),
    endereco: Cypress.env("profissional_endereco_put"),
    telefoneFixo: Cypress.env("profissional_telefone_fixo_put").replace(
      /\s/g,
      ""
    ),
    telefoneCelular: Cypress.env("profissional_celular_put").replace(/\s/g, ""),
    email: Cypress.env("profissional_email"),
  };

  return payload;
}

export function payloadPostServico() {
  const payload = {
    nome: `Serviço ${uuid.v4().substring(24)}`,
    descricao: `Descrição Serviço ${uuid.v4().substring(24)}`,
    valor: 2000.0,
    profissionais: [
      {
        id: 1,
      },
    ],
  };

  return payload;
}

export function payloadPutServico(servico_id) {
  const payload = {
    id: servico_id,
    nome: `Serviço PUT ${uuid.v4().substring(24)}`,
    descricao: `Descrição Serviço PUT ${uuid.v4().substring(24)}`,
    valor: 200.0,
    profissionais: [
      {
        id: 2,
      },
    ],
  };

  return payload;
}
