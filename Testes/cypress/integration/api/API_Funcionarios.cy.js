import {payloadFuncionarioExistente, payloadFuncionario, payloadAlterarFuncionario, payloadDelFuncionario} from "../../support/Utils/generatePayload";
import {cadastrarFuncionarios, validaCampos} from "../../fixtures/funcionario.json";

describe("/api/v1/funcionario", () => {
  before(() => {
    cy.clearFuncionarios()
  })

  context("POST", () => {
    cadastrarFuncionarios.forEach(function (payload) {
      it(`Deve cadastrar funcionario: ${payload.pessoa.nome}`, () => {
        cy.PostProfissional(payload).then((res) => {
          expect(res.status).to.eql(201);
          expect(res.body.pessoa.nome).to.eql(payload.pessoa.nome);
          expect(res.body.pessoa.telefone).to.eql(payload.pessoa.telefone);
          expect(res.body.pessoa.email).to.eql(payload.pessoa.email);
          expect(res.body.login).to.eql(payload.login);
          expect(res.body.senha).to.eql(payload.senha);
          expect(res.body.comissao).to.eql(payload.comissao);
        });
      });
    });

    it("Deve retornar erro ao cadastrar funcionario duplicado", () => {
      const payload = payloadFuncionarioExistente();
      cy.PostProfissional(payload).then((res) => {
        expect(res.status).to.eql(409);
        expect(res.body.mensagens[0]).contain(`Funcionario Já Cadastrado! CPF: ${payload.pessoa.cpfOuCnpj}`);
      });
    });
  });

  context("Validar campos obrigatórios", () => {
    validaCampos.forEach(function (payload) {
      it(`${payload.pessoa.campo}`, () => {
        cy.PostProfissional(payload).then((res) => {
          expect(res.status).to.eql(400);
          expect(res.body.mensagens[0]).contain(payload.pessoa.message);
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
        expect(res.body.id).to.eql(id);
        expect(res.body.pessoa.nome).to.eql(payload.pessoa.nome);
        expect(res.body.pessoa.telefone).to.eql(payload.pessoa.telefone);
        expect(res.body.pessoa.email).to.eql(payload.pessoa.email);
        expect(res.body.pessoa.tipoPessoa).to.eql(payload.pessoa.tipoPessoa);
        expect(res.body.pessoa.cpfOuCnpj).to.eql(payload.pessoa.cpfOuCnpj);
        expect(res.body.pessoa.estadoBrasileiro).to.eql(payload.pessoa.estadoBrasileiro);
        expect(res.body.login).to.eql(payload.login);
        expect(res.body.senha).to.eql(payload.senha);
        expect(res.body.comissao).to.eql(payload.comissao);
      });
    });

    it("Deve retornar erro ao pesquisar funcionário inexistente", () => {
      cy.GetAllProfissionalById("99999").then((res) => {
        expect(res.status).to.eql(404);
        expect(res.body.mensagens[0]).to.eql("Funcionario Não Encontrado! ID: 99999");
        expect(res.body.httpStatus).to.eql("NOT_FOUND");
      });
    })
  });

  context("PUT", () => {
    it("Deve editar funcionário", () => {
      const id = Cypress.env("func_id");
      const payload = payloadAlterarFuncionario(id);
      cy.PutProfissional(payload).then((res) => {
        expect(res.status).to.eql(200);
        expect(res.body.id).to.eql(payload.id);
        expect(res.body.pessoa.nome).to.eql(payload.pessoa.nome);
        expect(res.body.pessoa.telefone).to.eql(payload.pessoa.telefone);
        expect(res.body.pessoa.email).to.eql(payload.pessoa.email);
        expect(res.body.pessoa.tipoPessoa).to.eql(payload.pessoa.tipoPessoa);
        expect(res.body.pessoa.cpfOuCnpj).to.eql(payload.pessoa.cpfOuCnpj);
        expect(res.body.pessoa.estadoBrasileiro).to.eql(payload.pessoa.estadoBrasileiro);
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

    it("Deve retornar erro ao excluir profissional inexistente", () => {
      const id = Cypress.env("func_id");
      cy.DeleteProfissional(id).then((res) => {
        expect(res.status).to.eql(404);
        expect(res.body.mensagens[0]).to.eql(`Funcionario Não Encontrado! ID: ${id}`);
        expect(res.body.httpStatus).to.eql("NOT_FOUND");
      });
    });
  });
});
