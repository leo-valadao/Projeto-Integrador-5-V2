Cypress.Commands.add("GetPessoa", () => {
    cy.api({
      method: "POST",
      url: "https://www.4devs.com.br/ferramentas_online.php",
      form: true,
      body: {
        acao: "gerar_pessoa",
        sexo: "M",
        idade: 0,
        cep_estado: "GO",
        txt_qtde: 2,
        cep_cidade: 2174,
        pontuacao: "S",
      },
    }).then((res) => {
      expect(res.status).to.eq(200);
      expect(res.body).to.be.a('array')
      Cypress.env('nome', res.body[0].nome)
      Cypress.env('cpf', res.body[0].cpf)
      Cypress.env('endereco', res.body[0].endereco)
      Cypress.env('telefone_fixo', res.body[0].telefone_fixo)
      Cypress.env('celular', res.body[0].celular)
      Cypress.env('email', res.body[0].email)
  
      Cypress.env('endereco_put', res.body[1].endereco)
      Cypress.env('telefone_fixo_put', res.body[1].telefone_fixo)
      Cypress.env('celular_put', res.body[1].celular)
    });
  });
  
  Cypress.Commands.add("GetProfissional", () => {
    cy.request({
      method: "POST",
      url: "https://www.4devs.com.br/ferramentas_online.php",
      form: true,
      body: {
        acao: "gerar_pessoa",
        sexo: "I",
        idade: 0,
        cep_estado: "GO",
        txt_qtde: 2,
        cep_cidade: 2174,
        pontuacao: "S",
      },
    }).then((res) => {
      expect(res.status).to.eq(200);
      expect(res.body).to.be.a('array')
      Cypress.env('profissional_nome', res.body[0].nome)
      Cypress.env('profissional_cpf', res.body[0].cpf)
      Cypress.env('profissional_endereco', res.body[0].endereco)
      Cypress.env('profissional_telefone_fixo', res.body[0].telefone_fixo)
      Cypress.env('profissional_celular', res.body[0].celular)
      Cypress.env('profissional_email', res.body[0].email)
  
      Cypress.env('profissional_endereco_put', res.body[1].endereco)
      Cypress.env('profissional_telefone_fixo_put', res.body[1].telefone_fixo)
      Cypress.env('profissional_celular_put', res.body[1].celular)
    });
  });
  