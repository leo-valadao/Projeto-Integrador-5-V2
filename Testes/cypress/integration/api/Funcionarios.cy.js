import {
  payloadPostProfissional,
  payloadPutProfissional,
  payloadDelProfissional,
} from "../../support/Utils/generatePayload";

describe("/api/v1/funcionario", () => {
  context("POST", () => {
    before(() => {
      cy.GetProfissional().then((res) => {
        expect(res.status).to.eql(200);
        Cypress.env("profissional_nome", res.body[0].nome);
        Cypress.env("profissional_celular", res.body[0].celular);
        Cypress.env("profissional_email", res.body[0].email);
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
        Cypress.env("profissional_id", res.body.content[1].id);
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

  context("PUT", () => {
    before(() => {
      cy.GetProfissional().then((res) => {
        expect(res.status).to.eq(200);
        Cypress.env("putNome_prof", res.body[1].nome);
        Cypress.env("putCpf_prof", res.body[1].cpf);
        Cypress.env("putTelefone_prof", res.body[1].celular);
        Cypress.env("putEmail_prof", res.body[1].email);
        Cypress.env("putSenha_prof", res.body[1].senha);
      });
    });

    it("Deve editar funcionário", () => {
      const id = Cypress.env("profissional_id");
      const payload = payloadPutProfissional(id);
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
    it("Deve excluir profissional", () => {
      const id = Cypress.env("profissional_id");
      cy.DeleteProfissional(id).then((res) => {
        expect(res.status).to.eql(204);
      });
    });
  });
});
