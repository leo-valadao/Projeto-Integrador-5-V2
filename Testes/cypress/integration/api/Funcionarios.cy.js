import { payloadPostProfissional } from "../../support/Utils/generatePayload";

describe("/api/v1/funcionario", () => {
  context("POST", () => {
    before(() => {
      cy.GetProfissional().then((res) => {
        expect(res.status).to.eql(200);
        Cypress.env("profissional_nome", res.body[0].nome);
        Cypress.env("profissional_celular", res.body[0].celular);
        Cypress.env("profissional_email", res.body[0].email);
        Cypress.env("profissional_uf", res.body[0].estado);
        Cypress.env("profissional_cpf", res.body[0].cpf);
        Cypress.env("profissional_senha", res.body[0].senha);
      });
    });
    it("Deve cadastrar funcionario", () => {
      const payload = payloadPostProfissional();
      cy.PostProfissional(payload).then((res) => {
        expect(res.status).to.eql(201);
        expect(res.body.nome).to.eql(payload.nome);
        expect(res.body.telefone).to.eql(payload.telefone);
        expect(res.body.email).to.eql(payload.email);
        expect(res.body.uf).to.eql(payload.uf);
        expect(res.body.login).to.eql(payload.login);
        expect(res.body.senha).to.eql(payload.senha);
        expect(res.body.comissao).to.eql(payload.comissao);
        Cypress.env("id_funcionario", res.body.id);
      });
    });

    it("Deve retornar erro ao cadastrar funcionario duplicado", () => {
      const payload = payloadPostProfissional();
      cy.PostProfissional(payload).then((res) => {
        expect(res.status).to.eql(500);
      });
    });
  });

  context("GET", () => {
    it("Deve retornar todos profissionais", () => {
      cy.GetAllProfissionais().then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.content).to.be.a("array");
      });
    });
    it("Deve retornar profissional por Id", () => {
      const id = Cypress.env("id_funcionario");
      const payload = payloadPostProfissional();
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
});
