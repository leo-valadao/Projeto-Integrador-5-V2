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
  cy.request({
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

Cypress.Commands.add("PostClienteDel", (payload) => {
  cy.request({
    method: "POST",
    url: `${url}/cliente`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    expect(response.status).to.eql(201);
    Cypress.env("id_delete", response.body.id);
  });
});
