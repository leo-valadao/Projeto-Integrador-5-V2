import {
  payloadPostAgendamento,
  payloadAgendamento,
  payloadAgendamentoPut,
} from "../../support/Utils/generatePayload";
import { validaCampos } from "../../fixtures/agendamento.json";

describe("/api/v1/agendamento", () => {
  before(() => {
    cy.clearAgendamentos();
    cy.GetRandomClientId();
    cy.GetRandomProfissionailId();
    cy.GetRandomServicosId();
  });

  context("Valida campos obrigatórios", () => {
    validaCampos.forEach(function (agendamento) {
      it(`${agendamento.campo}`, () => {
        cy.PostAgendamento(agendamento).then((res) => {
          expect(res.status).to.eql(400);
          expect(res.body.mensagens[0]).contain(agendamento.message);
        });
      });
    });
  });

  context("POST", () => {
    it("Deve cadastrar agendamento com sucesso", () => {
      const payload = payloadPostAgendamento();
      cy.PostAgendamento(payload).then((res) => {
        expect(res.status).to.eql(201);
      });
    });
  });

  context("GET", () => {
    before(() => {
      const payload = payloadAgendamento();
      cy.PostAndGetIdAgendamento(payload);
    });
    it("Deve retornar todos agendamentos", () => {
      cy.GetAllAgendamentos().then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.content).to.be.a("array");
        expect(res.body.content).length(2);
      });
    });

    it("Deve retornar agendamento por ID", () => {
      const payload = payloadAgendamento();
      cy.GetAgendamentoById(Cypress.env("agendamento_id")).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(Cypress.env("agendamento_id"));
        expect(res.body.data).to.eql(payload.data);
        expect(res.body.hora).to.eql(payload.hora);
        expect(res.body.status).to.eql(payload.status);
        expect(res.body.observacao).to.eql(payload.observacao);
        expect(res.body.cliente.id).to.eql(payload.cliente.id);
        expect(res.body.funcionario.id).to.eql(payload.funcionario.id);
        expect(res.body.servico.id).to.eql(payload.servico.id);
      });
    });

    it("Deve retornar erro ao pesquisar agendamento com ID inexistente", () => {
      cy.GetAgendamentoById("999").then((res) => {
        expect(res.status).to.eql(404);
        expect(res.body.mensagens[0]).to.eql(
          "Agendamento Não Encontrado! ID: 999"
        );
      });
    });
  });

  context("PUT", () => {
    it("Deve editar agendamento", () => {
      const payload = payloadAgendamentoPut();
      cy.PutAgendamento(payload).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(payload.id);
        expect(res.body.data).to.eql(payload.data);
        expect(res.body.hora).to.eql(payload.hora);
        expect(res.body.status).to.eql(payload.status);
        expect(res.body.observacao).to.eql(payload.observacao);
        expect(res.body.servico.id).to.eql(payload.servico.id);
        expect(res.body.funcionario.id).to.eql(payload.funcionario.id);
        expect(res.body.cliente.id).to.eql(payload.cliente.id);
      });
    });
  });

  context("DELETE", () => {
    it("Deve excluir agendamento", () => {
      cy.DeleteAgendamento(Cypress.env("agendamento_id")).then((res) => {
        expect(res.status).to.eql(204);
      });
    });

    it("Deve retornar erro ao excluir agendamento inexistente", () => {
      const id = Cypress.env("agendamento_id")
      cy.DeleteAgendamento(id).then((res) => {
        expect(res.status).to.eql(404);
        expect(res.body.mensagens[0]).to.eql(`Agendamento Não Encontrado! ID: ${id}`)
      });
    });
  });
});
