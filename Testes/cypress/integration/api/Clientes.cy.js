import {
  payloadCliente,
  payloadClienteExistente,
  payloadAlterarCliente,
  payloadDelCliente,
} from "../../support/Utils/generatePayload";
import { validaCampos, cadastrarClientes } from "../../fixtures/cliente.json";

describe("/api/v1/cliente", () => {
  before(() => {
    cy.clearClientes();
  });

  context("POST", () => {
    cadastrarClientes.forEach(function (payload) {
      it(`Deve cadastrar cliente: ${payload.pessoa.nome}`, () => {
        cy.PostCliente(payload).then((res) => {
          expect(res.status).to.eql(201);
          expect(res.body.pessoa.nome).to.eql(payload.pessoa.nome);
          expect(res.body.pessoa.telefone).to.eql(payload.pessoa.telefone);
          expect(res.body.pessoa.email).to.eql(payload.pessoa.email);
          expect(res.body.pessoa.tipoPessoa).to.eql(payload.pessoa.tipoPessoa);
          expect(res.body.pessoa.cpfOuCnpj).to.eql(payload.pessoa.cpfOuCnpj);
        });
      });
    });

    it("Deve retornar erro ao cadastrar cpf existente", () => {
      const payload = payloadClienteExistente();
      cy.PostCliente(payload).then((res) => {
        expect(res.status).to.eql(409);
        expect(res.body.mensagens[0]).contain(
          `Cliente Já Cadastrado! CPF: ${payload.pessoa.cpfOuCnpj}`
        );
      });
    });
  });
  
  context("Validar campos obrigatórios", () => {
    validaCampos.forEach(function (cliente) {
      it(`${cliente.pessoa.campo}`, () => {
        cy.PostCliente(cliente).then((res) => {
          expect(res.status).to.eql(400);
          expect(res.body.mensagens[0]).contain(cliente.pessoa.message);
        });
      });
    });
  });

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
        expect(res.body.id).to.eql(id);
        expect(res.body.pessoa.nome).to.eql(payload.pessoa.nome);
        expect(res.body.pessoa.telefone).to.eql(payload.pessoa.telefone);
        expect(res.body.pessoa.email).to.eql(payload.pessoa.email);
        expect(res.body.pessoa.cpfOuCnpj).to.eql(payload.pessoa.cpfOuCnpj);
        expect(res.body.pessoa.tipoPessoa).to.eql(payload.pessoa.tipoPessoa);
        expect(res.body.pessoa.estadoBrasileiro).to.eql(
          payload.pessoa.estadoBrasileiro
        );
        expect(res.body.pessoa.alergias).to.eql(payload.pessoa.alergias);
      });
    });

    it("Deve retornar erro ao pesquisar cliente inexistente", () => {
      cy.GetClienteById("99999").then((res) => {
        expect(res.status).to.eql(404);
        expect(res.body.mensagens[0]).to.eql(`Cliente Não Encontrado! ID: 99999`);
        expect(res.body.httpStatus).to.eql("NOT_FOUND");
      });
    });
  });

  context("PUT", () => {
    it("Deve editar cliente", () => {
      const id = Cypress.env("cli_id");
      const payload = payloadAlterarCliente(id);

      cy.PutCliente(payload).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(payload.id);
        expect(res.body.pessoa.nome).to.eql(payload.pessoa.nome);
        expect(res.body.pessoa.cpfOuCnpj).to.eql(payload.pessoa.cpfOuCnpj);
        expect(res.body.pessoa.tipoPessoa).to.eql(payload.pessoa.tipoPessoa);
        expect(res.body.pessoa.telefone).to.eql(payload.pessoa.telefone);
        expect(res.body.pessoa.email).to.eql(payload.pessoa.email);
        expect(res.body.pessoa.estadoBrasileiro).to.eql(payload.pessoa.estadoBrasileiro);
        expect(res.body.pessoa.alergias).to.eql(payload.pessoa.alergias);
      });
    });
  });

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

    it("Deve retornar erro ao excluir cliente inexistente", () => {
      cy.DeleteCliente("99999").then((res) => {
        expect(res.status).to.eql(404);
        expect(res.body.mensagens[0]).to.eql("Cliente Não Encontrado! ID: 99999");
        expect(res.body.httpStatus).to.eql("NOT_FOUND");
      });
    });
  });
});
