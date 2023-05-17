import "@bahmutov/cy-api";
import "./commands";
import "./ApiCommands/agendamentoCommands";
import "./ApiCommands/clientesCommands";
import "./ApiCommands/profissionaisCommands";
import "./ApiCommands/servicosCommands";

// before(() => {
//   cy.GetPessoa().then((res) => {
//     expect(res.status).to.eq(200);
//     Cypress.env("nome", res.body[0].nome);
//     Cypress.env("cpf", res.body[0].cpf);
//     Cypress.env("endereco", res.body[0].endereco);
//     Cypress.env("telefone", res.body[0].celular);
//     Cypress.env("email", res.body[0].email);

//     Cypress.env("endereco_put", res.body[1].endereco);
//     Cypress.env("telefone_fixo_put", res.body[1].telefone_fixo);
//     Cypress.env("celular_put", res.body[1].celular);
//   });
//   cy.GetProfissional();
// });
