import {payloadCliente, payloadClienteExistente, payloadAlterarCliente, payloadDelCliente} from "../../support/Utils/generatePayload";
import { validaCampos, cadastrarClientes } from "../../fixtures/cliente.json";

describe("/api/v1/cliente", () => {
  before(() => {
    cy.clearClientes();
  });
  context("POST", () => {
    cadastrarClientes.forEach(function (payload) {
      it(`Deve cadastrar cliente: ${payload.nome}`, () => {
        cy.PostCliente(payload).then((res) => {
          expect(res.status).to.eql(201);
          expect(res.body.nome).to.eql(payload.nome);
          expect(res.body.telefone).to.eql(payload.telefone);
          expect(res.body.email).to.eql(payload.email);
          expect(res.body.cpf).to.eql(payload.cpf);
        });
      });
    });

    it("Deve retornar erro ao cadastrar cpf existente", () => {
      const payload = payloadClienteExistente();
      cy.PostCliente(payload).then((res) => {
        expect(res.status).to.eql(500);
      });
    });
  });
  //OK
  context("Validar campos obrigatórios", () => {
    validaCampos.forEach(function (cliente) {
      it(`${cliente.campo}`, () => {
        cy.PostCliente(cliente).then((res) => {
          expect(res.status).to.eql(400);
          expect(res.body.mensagem).to.eql(cliente.message);
        });
      });
    });
  });

  //TODO: Implementar msg de erro ao pesquisar cliente inexistente
  context("GET", () => {
    before(() => {
      const payload = payloadCliente();
      cy.postAndGetIdCliente(payload);
    });

    it("Deve retornar todos os clientes cadastrados", () => {
      cy.GetAllClients().then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.content).to.be.a("array");
      });
    });

    it("Deve retornar cliente por ID", () => {
      const id = Cypress.env("cli_id");
      const payload = payloadCliente();
      cy.GetClienteById(id).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.telefone).to.eql(payload.telefone);
        expect(res.body.email).to.eql(payload.email);
        expect(res.body.cpf).to.eql(payload.cpf);
        expect(res.body.estadoBrasileiro).to.eql(payload.estadoBrasileiro);
        expect(res.body.alergias).to.eql(payload.alergias);
      });
    });
  });

  //OK
  context("PUT", () => {
    it("Deve editar cliente", () => {
      const id = Cypress.env("cli_id");
      const payload = payloadAlterarCliente(id);

      cy.PutCliente(payload).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(payload.id);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.cpf).to.eql(payload.cpf);
        expect(res.body.telefone).to.eql(payload.telefone);
        expect(res.body.email).to.eql(payload.email);
        expect(res.body.estadoBrasileiro).to.eql(payload.estadoBrasileiro);
        expect(res.body.alergias).to.eql(payload.alergias);
      });
    });
  });

  //TODO: Implementar msg de erro ao tentar excluir cliente inexistente
  context("DELETE", () => {
    before(() => {
      const payload = payloadDelCliente();
      cy.postAndGetIdCliente(payload);
    });

    it("Deve excluir cliente", () => {
      const id = Cypress.env("cli_id");
      cy.DeleteCliente(id).then((res) => {
        expect(res.status).to.eql(204);
      });
    });
  });
});
