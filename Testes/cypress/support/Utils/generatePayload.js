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
    estadoBrasileiro: "GOIAS",
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
    estadoBrasileiro: "GOIAS",
    email: Cypress.env("emailPut"),
    alergias: "Shampoo de Camomila",
  };

  return payload;
}

export function payloadPostProfissional() {
  const payload = {
    nome: Cypress.env("profissional_nome"),
    telefone: Cypress.env("profissional_celular").replace(/\s/g, ""),
    email: Cypress.env("profissional_email"),
    estadoBrasileiro: "GOIAS",
    cpf: Cypress.env("profissional_cpf"),
    login: Cypress.env("profissional_senha"),
    senha: Cypress.env("profissional_senha"),
    comissao: 12.5,
  };

  return payload;
}

export function payloadDelProfissional() {
  const payload = {
    nome: Cypress.env("profissionalDel_nome"),
    telefone: Cypress.env("profissionalDel_celular").replace(/\s/g, ""),
    email: Cypress.env("profissionalDel_email"),
    estadoBrasileiro: "GOIAS",
    cpf: Cypress.env("profissionalDel_cpf"),
    login: Cypress.env("profissionalDel_senha"),
    senha: Cypress.env("profissionalDel_senha"),
    comissao: 12.5,
  };

  return payload;
}

export function payloadPutProfissional(id) {
  const payload = {
    id: id,
    nome: Cypress.env("putNome_prof"),
    cpf: Cypress.env("putCpf_prof"),
    estadoBrasileiro: "GOIAS",
    telefone: Cypress.env("putTelefone_prof").replace(/\s/g, ""),
    email: Cypress.env("putEmail_prof"),
    login: Cypress.env("putSenha_prof"),
    senha: Cypress.env("putSenha_prof"),
    comissao: 10.00,
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
