export const url = Cypress.env("apiUrl");

//TODO: ADAPTAR AO NOVO PROJETO BACK-END
Cypress.Commands.add("PostAgendamento", (payload) => {
  cy.api({
    method: "POST",
    url: `${url}/agendamento`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});

Cypress.Commands.add("GetAllAgendamentos", () => {
  cy.api({
    method: "GET",
    url: `${url}/agendamento`,
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});

Cypress.Commands.add("GetAgendamentoById", (id) => {
  cy.api({
    method: "GET",
    url: `${url}/agendamento`,
    qs: {
      id: id,
    },
    failOnStatusCode: false,
  }).then(function (response) {
    return response;
  });
});

Cypress.Commands.add("PutAgendamento", (payload) => {
  cy.api({
    method: "PUT",
    url: `${url}/agendamento`,
    body: payload,
    failOnStatusCode: false,
  }).then((response) => {
    return response;
  });
});

Cypress.Commands.add("DeleteAgendamento", (id) => {
  cy.api({
    method: "DELETE",
    url: `${url}/agendamento`,
    qs: {
      id: id,
    },
    failOnStatusCode: false,
  }).then(function (response) {
    return response;
  });
});
