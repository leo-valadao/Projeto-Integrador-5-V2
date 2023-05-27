import {
  payloadPostCliente,
  payloadPutCliente,
} from "../../support/Utils/generatePayload";
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
        Cypress.env("id_cliente", res.body.id);
      });
    });

    it("Deve retornar erro ao cadastrar cpf existente", () => {
      const payload = payloadPostCliente();
      cy.PostCliente(payload).then((res) => {
        expect(res.status).to.eql(500);
      });
    });
  });

  //TODO: Implementar msg de erro ao pesquisar cliente inexistente
  context("GET", () => {
    it("Deve retornar todos os clientes cadastrados", () => {
      cy.GetAllClients().then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.content).to.be.a("array");
        Cypress.env("id_edit", res.body.content[0].id);
      });
    });

    it("Deve retornar cliente por ID", () => {
      const payload = payloadPostCliente();
      const id = Cypress.env("id_cliente");
      cy.GetClienteById(id).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.telefone).to.eql(payload.telefone);
        expect(res.body.email).to.eql(payload.email);
        expect(res.body.cpf).to.eql(payload.cpf);
        expect(res.body.uf).to.eql("GO");
        expect(res.body.alergias).to.eql("Nenhuma");
      });
    });
  });

  context("PUT", () => {
    before(() => {
      cy.GetPessoa().then((res) => {
        expect(res.status).to.eq(200);
        Cypress.env("nomePut", res.body[1].nome);
        Cypress.env("cpfPut", res.body[1].cpf);
        Cypress.env("telefonePut", res.body[1].celular);
        Cypress.env("emailPut", res.body[1].email);
      });
    });
    it("Deve editar cliente", () => {
      const id = Cypress.env("id_edit");
      const payloadPut = payloadPutCliente(id);
      cy.PutCliente(payloadPut).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(payloadPut.id);
        expect(res.body.nome).to.eql(payloadPut.nome);
        expect(res.body.telefone).to.eql(payloadPut.telefone);
        expect(res.body.email).to.eql(payloadPut.email);
        expect(res.body.uf).to.eql(payloadPut.uf);
        expect(res.body.alergias).to.eql(payloadPut.alergias);
      });
    });
  });

  //TODO: Implementar msg de erro ao tentar excluir cliente inexistente
  context("DELETE", () => {
    before(() => {
      cy.GetPessoa().then((res) => {
        expect(res.status).to.eq(200);
        Cypress.env("nome", res.body[1].nome);
        Cypress.env("cpf", res.body[1].cpf);
        Cypress.env("telefone", res.body[1].celular);
        Cypress.env("email", res.body[1].email);
      });

      const payload = payloadPostCliente();
      cy.PostClienteDel(payload);
    });

    it("Deve excluir cliente", () => {
      const id = Cypress.env("id_delete");
      cy.DeleteCliente(id).then((res) => {
        expect(res.status).to.eql(204);
      });
    });
  });

  //TODO: Validação de telefone está com erro
  context("Validar campos obrigatórios", () => {
    validaCampos.forEach(function (cliente) {
      it(`${cliente.campo}`, () => {
        cy.PostCliente(cliente).then((res) => {
          expect(res.status).to.eql(400);
          expect(res.body.errors[0].defaultMessage).to.eql(cliente.message);
        });
      });
    });
  });
});
