export const url = Cypress.env("apiUrl");

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
  cy.api({
    method: "POST",
    url: `${url}/funcionario`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    expect(response.status).to.eql(201);
    Cypress.env("func_id", response.body.id);
  });
});

Cypress.Commands.add("clearFuncionarios", () => {
  cy.GetAllProfissionais().then((response) => {
    response.body.content.forEach((data) => {
      cy.DeleteProfissional(data.id).then((res) => {
        cy.log("Remove ID: " + data.id);
        expect(res.status).to.eql(204);
      });
    });
  });
});

Cypress.Commands.add("clearClientes", () => {
  cy.GetAllClients().then((response) => {
    response.body.content.forEach((data) => {
      cy.DeleteCliente(data.id).then((res) => {
        cy.log("Remove ID: " + data.id);
        expect(res.status).to.eql(204);
      });
    });
  });
});
