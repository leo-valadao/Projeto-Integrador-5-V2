import { validaCampos, servicos } from "../../fixtures/servico.json";
import {payloadPostServico, payloadPutServico, payloadPostServicoDel } from "../../support/Utils/generatePayload";

describe("/api/v1/servico", () => {
  before(() => {
    cy.clearServicos();
  });

  context("Validar campos Obrigatórios", () => {
    validaCampos.forEach(function (payload) {
      it(`${payload.campo}`, () => {
        cy.PostServico(payload).then((res) => {
          expect(res.status).to.eql(400);
          expect(res.body.mensagens[0]).contain(`${payload.message}`);
        });
      });
    });
  });

  context("POST", () => {
    servicos.forEach(function (payload) {
      it(`Deve cadastrar serviço ${payload.nome} com sucesso`, () => {
        cy.PostServico(payload).then((res) => {
          expect(res.status).to.eql(201);
          expect(res.body.nome).to.eql(payload.nome);
          expect(res.body.descricao).to.eql(payload.descricao);
          expect(res.body.precoCusto).to.eql(payload.precoCusto);
          expect(res.body.precoVenda).to.eql(payload.precoVenda);
        });
      });
    });
  });

  context("GET", () => {
    before(() => {
      const payload = payloadPostServico();
      cy.PostAndGetIdServico(payload);
    });

    it("Deve retornar todos os serviços cadastrados", () => {
      cy.GetAllServicos().then((res) => {
        expect(res.status).to.equal(200);
        expect(res.body.content).to.be.a("array");
        expect(res.body.content).length(11);
      });
    });

    it("Deve retornar serviço por ID", () => {
      const payload = payloadPostServico();
      const id = Cypress.env("servico_id");
      cy.GetServicoById(id).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(id);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.descricao).to.eql(payload.descricao);
        expect(res.body.precoCusto).to.eql(payload.precoCusto);
        expect(res.body.precoVenda).to.eql(payload.precoVenda);
      });
    });

    it("Deve retornar erro ao pesquisar serviço com ID inexistente", () => {
      cy.GetServicoById(999).then((res) => {
        expect(res.status).to.eql(404);
        expect(res.body.mensagens[0]).contain("Servico Não Encontrado! ID: 999");
        expect(res.body.httpStatus).to.eql("NOT_FOUND");
      });
    });
  });

  context("PUT", () => {
    it("Deve editar serviço com sucesso", () => {
      const payload = payloadPutServico(Cypress.env("servico_id"));
      cy.PutServico(payload).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(payload.id);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.descricao).to.eql(payload.descricao);
        expect(res.body.precoCusto).to.eql(payload.precoCusto);
        expect(res.body.precoVenda).to.eql(payload.precoVenda);
      });
    });
  });

  context("DELETE", () => {
    before(() => {
      const payload = payloadPostServicoDel();
      cy.PostAndGetIdServico(payload);
    });

    it("Deve excluir serviço com sucesso", () => {
      cy.DeleteServico(Cypress.env("servico_id")).then((res) => {
        expect(res.status).to.eql(204);
      });
    });

    it("Deve retornar erro ao excluir serviço com ID inexistente", () => {
      cy.DeleteServico(Cypress.env("servico_id")).then((res) => {
        expect(res.status).to.eql(404);
        expect(res.body.mensagens[0]).contain(`Servico Não Encontrado! ID: ${Cypress.env("servico_id")}`);
        expect(res.body.httpStatus).to.eql("NOT_FOUND");
      });
    });
  });
});
