import {payloadFuncionarioExistente, payloadFuncionario, payloadAlterarFuncionario, payloadDelFuncionario} from "../../support/Utils/generatePayload";
import {cadastrarFuncionarios, validaCampos} from "../../fixtures/funcionario.json";

describe("/api/v1/funcionario", () => {
  before(() => {
    cy.clearFuncionarios()
  })

  context("POST", () => {
    cadastrarFuncionarios.forEach(function (payload) {
      it(`Deve cadastrar funcionario: ${payload.nome}`, () => {
        cy.PostProfissional(payload).then((res) => {
          expect(res.status).to.eql(201);
          expect(res.body.nome).to.eql(payload.nome);
          expect(res.body.telefone).to.eql(payload.telefone);
          expect(res.body.email).to.eql(payload.email);
          expect(res.body.login).to.eql(payload.login);
          expect(res.body.senha).to.eql(payload.senha);
          expect(res.body.comissao).to.eql(payload.comissao);
        });
      });
    });

    it("Deve retornar erro ao cadastrar funcionario duplicado", () => {
      const payload = payloadFuncionarioExistente();
      cy.PostProfissional(payload).then((res) => {
        expect(res.status).to.eql(500);
      });
    });
  });

  context("Validar campos obrigatórios", () => {
    validaCampos.forEach(function (payload) {
      it(`${payload.campo}`, () => {
        cy.PostProfissional(payload).then((res) => {
          expect(res.status).to.eql(400);
          expect(res.body.mensagem).to.eql(payload.message);
        });
      });
    });
  });

  context("GET", () => {
    before(() => {
      const payload = payloadFuncionario();
      cy.PostAndGetIdProfissional(payload);
    });

    it("Deve retornar todos profissionais", () => {
      cy.GetAllProfissionais().then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.content).to.be.a("array");
      });
    });

    it("Deve retornar profissional por Id", () => {
      const id = Cypress.env("func_id");
      const payload = payloadFuncionario();
      cy.GetAllProfissionalById(id).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.telefone).to.eql(payload.telefone);
        expect(res.body.email).to.eql(payload.email);
        expect(res.body.uf).to.eql(payload.uf);
        expect(res.body.login).to.eql(payload.login);
        expect(res.body.senha).to.eql(payload.senha);
        expect(res.body.comissao).to.eql(payload.comissao);
      });
    });
  });

  context("PUT", () => {
    it("Deve editar funcionário", () => {
      const id = Cypress.env("func_id");
      const payload = payloadAlterarFuncionario(id);
      cy.PutProfissional(payload).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(payload.id);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.telefone).to.eql(payload.telefone);
        expect(res.body.email).to.eql(payload.email);
        expect(res.body.estadoBrasileiro).to.eql(payload.estadoBrasileiro);
        expect(res.body.login).to.eql(payload.login);
        expect(res.body.senha).to.eql(payload.senha);
        expect(res.body.comissao).to.eql(payload.comissao);
      });
    });
  });

  context("DELETE", () => {
    before(() => {
      const payload = payloadDelFuncionario();
      cy.PostAndGetIdProfissional(payload);
    });
    it("Deve excluir profissional", () => {
      const id = Cypress.env("func_id");
      cy.DeleteProfissional(id).then((res) => {
        expect(res.status).to.eql(204);
      });
    });
  });
});
