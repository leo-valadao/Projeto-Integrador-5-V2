import "@bahmutov/cy-api";
import "./commands";
import "./ApiCommands/agendamentoCommands";
import "./ApiCommands/clientesCommands";
import "./ApiCommands/profissionaisCommands";
import "./ApiCommands/servicosCommands";

before(() => {
  cy.GetPessoa();
  cy.GetProfissional();
});
