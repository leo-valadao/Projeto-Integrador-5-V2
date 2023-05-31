export const url = Cypress.env("apiUrl");

Cypress.Commands.add("PostProfissional", (payload) => {
  cy.api({
    method: "POST",
    url: `${url}/funcionario`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});

Cypress.Commands.add("GetAllProfissionais", () => {
  cy.api({
    method: "GET",
    url: `${url}/funcionario`,
    failOnStatusCode: false,
  }).then(function (response) {
    return response;
  });
});

Cypress.Commands.add("GetAllProfissionalById", (id) => {
  cy.api({
    method: "GET",
    url: `${url}/funcionario`,
    qs: {
      id: id,
    },
    failOnStatusCode: false,
  }).then(function (response) {
    return response;
  });
});

Cypress.Commands.add("PutProfissional", (payload) => {
  cy.api({
    method: "PUT",
    url: `${url}/funcionario`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});

Cypress.Commands.add("DeleteProfissional", (id) => {
  cy.api({
    method: "DELETE",
    url: `${url}/funcionario`,
    qs: {
      id: id,
    },
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});