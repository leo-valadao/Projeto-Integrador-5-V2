import { payloadPostCliente } from "../../support/Utils/generatePayload";
import { validaCampos } from "../../fixtures/cliente.json";

describe("/api/v1/cliente", () => {
  context("POST", () => {
    before(() => {
      cy.GetPessoa().then((res) => {
        expect(res.status).to.eq(200);
        Cypress.env("nome", res.body[0].nome);
        Cypress.env("cpf", res.body[0].cpf);
        Cypress.env("endereco", res.body[0].endereco);
        Cypress.env("telefone", res.body[0].celular);
        Cypress.env("email", res.body[0].email);
      });
    });
    it("Deve Cadastrar cliente com sucesso", () => {
      const payload = payloadPostCliente();
      cy.PostCliente(payload).then((res) => {
        expect(res.status).to.eql(201);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.telefone).to.eql(payload.telefone);
        expect(res.body.email).to.eql(payload.email);
        expect(res.body.cpf).to.eql(payload.cpf);
      });
    });

    it("Deve retornar erro ao cadastrar cpf existente", () => {
      const payload = payloadPostCliente();
      cy.PostCliente(payload).then((res) => {
        expect(res.status).to.eql(500);
      });
    });
  });

  context("Valida campos obrigatórios", () => {
    validaCampos.forEach(function (cliente) {
      it(`${cliente.campo}`, () => {
        cy.PostCliente(cliente).then((res) => {
          expect(res.status).to.eql(400);
          expect(res.body.errors[0].defaultMessage).to.eql(cliente.message)
        });
      });
    });
  });
  
  context("GET", () => {
    it("Deve retornar todos os clientes cadastrados", () => {
      cy.GetAllClients().then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.content).to.be.a("array");
      });
    });
  });
});
