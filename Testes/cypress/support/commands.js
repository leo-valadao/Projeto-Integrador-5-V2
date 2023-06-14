import { obterNumeroAleatorio } from "./Utils/generateDate";
export const url = Cypress.env("apiUrl");
export const random = obterNumeroAleatorio();

Cypress.Commands.add("GetPessoa", () => {
  cy.request({
    method: "POST",
    url: "https://www.4devs.com.br/ferramentas_online.php",
    form: true,
    body: {
      acao: "gerar_pessoa",
      sexo: "M",
      idade: 0,
      cep_estado: "GO",
      txt_qtde: 2,
      cep_cidade: 2174,
      pontuacao: "S",
    },
  }).then((res) => {
    return res;
  });
});

Cypress.Commands.add("GetProfissional", () => {
  cy.api({
    method: "POST",
    url: "https://www.4devs.com.br/ferramentas_online.php",
    form: true,
    body: {
      acao: "gerar_pessoa",
      sexo: "I",
      idade: 0,
      cep_estado: "GO",
      txt_qtde: 2,
      cep_cidade: 2174,
      pontuacao: "S",
    },
  }).then((res) => {
    return res;
  });
});

Cypress.Commands.add("postAndGetIdCliente", (payload) => {
  cy.request({
    method: "POST",
    url: `${url}/cliente`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    expect(response.status).to.eql(201);
    Cypress.env("cli_id", response.body.id);
  });
});

Cypress.Commands.add("PostAndGetIdProfissional", (payload) => {
  cy.request({
    method: "POST",
    url: `${url}/funcionario`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    expect(response.status).to.eql(201);
    Cypress.env("func_id", response.body.id);
  });
});

Cypress.Commands.add("PostAndGetIdServico", (payload) => {
  cy.request({
    method: "POST",
    url: `${url}/servico`,
    body: payload,
    failOnStatusCode: false,
  }).then(function (response) {
    expect(response.status).to.eql(201);
    Cypress.env("servico_id", response.body.id);
  });
});

Cypress.Commands.add("PostAndGetIdAgendamento", (payload) => {
  cy.request({
    method: "POST",
    url: `${url}/agendamento`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    expect(response.status).to.eql(201);
    Cypress.env("agendamento_id", response.body.id);
  });
});

Cypress.Commands.add("clearFuncionarios", () => {
  cy.request({
    method: "GET",
    url: `${url}/funcionario`,
    failOnStatusCode: false,
  }).then(function (response) {
    response.body.content.forEach((data) => {
      cy.request({
        method: "DELETE",
        url: `${url}/funcionario`,
        qs: {
          id: data.id,
        },
        failOnStatusCode: false,
      }).then((response) => {
        cy.log("Remove ID: " + data.id);
        expect(response.status).to.eql(204);
      });
    });
  });
});

Cypress.Commands.add("clearClientes", () => {
  cy.request({
    method: "GET",
    url: `${url}/cliente`,
    failOnStatusCode: false,
  }).then(function (response) {
    response.body.content.forEach((data) => {
      cy.request({
        method: "DELETE",
        url: `${url}/cliente`,
        qs: {
          id: data.id,
        },
        failOnStatusCode: false,
      }).then((response) => {
        cy.log("Remove ID: " + data.id);
        expect(response.status).to.eql(204);
      });
    });
  });
});

Cypress.Commands.add("clearServicos", () => {
  cy.request({
    method: "GET",
    url: `${url}/servico`,
    qs: {
      ordenarPo: "id",
    },
    failOnStatusCode: false,
  }).then((response) => {
    response.body.content.forEach((data) => {
      cy.request({
        method: "DELETE",
        url: `${url}/servico`,
        qs: {
          id: data.id,
        },
        failOnStatusCode: false,
      }).then((response) => {
        cy.log("Remove ID: " + data.id);
        expect(response.status).to.eql(204);
      });
    });
  });
});

Cypress.Commands.add("clearAgendamentos", () => {
  cy.request({
    method: "GET",
    url: `${url}/agendamento`,
    qs: {
      ordenarPo: "id",
    },
    failOnStatusCode: false,
  }).then((response) => {
    response.body.content.forEach((data) => {
      cy.request({
        method: "DELETE",
        url: `${url}/agendamento`,
        qs: {
          id: data.id,
        },
        failOnStatusCode: false,
      }).then((response) => {
        cy.log("Remove ID: " + data.id);
        expect(response.status).to.eql(204);
      });
    });
  });
});

Cypress.Commands.add("GetRandomClientId", () => {
  cy.request({
    method: "GET",
    url: `${url}/cliente`,
    failOnStatusCode: false,
  }).then(function (response) {
    Cypress.env("cliId_agendamento", response.body.content[random].id);
  });
});

Cypress.Commands.add("GetRandomProfissionailId", () => {
  cy.request({
    method: "GET",
    url: `${url}/funcionario`,
    failOnStatusCode: false,
  }).then(function (response) {
    Cypress.env("profId_agendamento", response.body.content[random].id);
  });
});

Cypress.Commands.add("GetRandomServicosId", () => {
  cy.request({
    method: "GET",
    url: `${url}/servico`,
    qs: {
      ordenarPo: "id",
    },
    failOnStatusCode: false,
  }).then(function (response) {
    Cypress.env("servfId_agendamento", response.body.content[random].id);
  });
});
