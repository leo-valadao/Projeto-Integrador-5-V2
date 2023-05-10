export const url = Cypress.env("apiUrl");
// var position = Math.floor(Math.random() * 9);

//TODO: ADAPTAR AO NOVO PROJETO BACK-END
Cypress.Commands.add("GetAllClients", () => {
  cy.api({
    method: "GET",
    url: `${url}/cliente`,
    failOnStatusCode: false,
  }).then(function (response) {
    return response;
  });
});

Cypress.Commands.add("GetClienteById", (id) => {
  cy.api({
    method: "GET",
    url: `${url}/cliente`,
    qs: {
      id: id,
    },
    failOnStatusCode: false,
  }).then(function (response) {
    return response;
  });
});

Cypress.Commands.add("PostCliente", (payload) => {
  cy.api({
    method: "POST",
    url: `${url}/cliente`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});

Cypress.Commands.add("PutCliente", (payload) => {
  cy.api({
    method: "PUT",
    url: `${url}/cliente`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});

Cypress.Commands.add("DeleteCliente", (cliente_id) => {
  cy.api({
    method: "DELETE",
    url: `${url}/cliente`,
    qs: {
      id: cliente_id,
    },
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});
