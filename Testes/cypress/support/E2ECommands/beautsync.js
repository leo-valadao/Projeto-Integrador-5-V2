class BeautySync {
  Cadastrar() {
    cy.step("Acessando Pagina Principal");
    cy.visit("/");
    cy.wait(5000);
    cy.contains("span", "BeautySync,").should("be.visible");

    cy.step("Cadastrando Cliente")
    cy.get('li[routerlink="/clientes"]').should("be.visible").click();
    cy.url().should("contain", "/clientes");
    cy.wait(1000);
    cy.get('button[label="Adicionar"]').should("be.visible").click();
    cy.contains("span", "Cadastro de Cliente").should("be.visible");
    cy.get('[data-cy="cliente.pessoa.nome"]').click().type("Cliente Teste");
    cy.get('[data-cy="cliente.pessoa.tipoPessoa"]').click();
    cy.get('li[aria-label="PESSOA_FISICA"]').should("be.visible").click();
    cy.get('[data-cy="cliente.pessoa.cpfOuCnpj"]').type("720.694.643-77");
    cy.get('[data-cy="cliente.pessoa.telefone"]').type("6299176-9758");
    cy.get('[data-cy="cliente.pessoa.email"]').type("pessoa_teste@ads.com");
    cy.get('[data-cy="cliente.pessoa.estadoBrasileiro"]').click();
    cy.get('li[aria-label="GOIAS"]').scrollIntoView().click();
    cy.get('[data-cy="cliente.alergias"]').click().type("Nenhuma");
    cy.wait(3000)
    cy.contains("button", "Salvar Cliente").click();
    cy.contains("div[role='alert']", "Cliente Inserido!").should("be.visible");
    cy.contains('td', 'Cliente Teste').should('be.visible')
    cy.wait(3000)

    cy.step("Cadastrando Funcionário")
    cy.get('li[routerlink="/funcionarios"]').should('be.visible').click()
    cy.url().should('contain', '/funcionarios')
    cy.wait(3000)
    cy.contains('button', 'Adicionar').should('be.visible').click()
    cy.contains('span', 'Cadastro de Funcionário').should('be.visible')
    cy.get('[data-cy="funcionario.pessoa.nome"]').click().type('Funcionário Teste')
    cy.get('[data-cy="funcionario.pessoa.tipoPessoa"]').click()
    cy.get('li[aria-label="PESSOA_FISICA"]').click()
    cy.get('[data-cy="funcionario.pessoa.cpfOuCnpj"]').click().type('048.673.414-52')
    cy.get('[data-cy="funcionario.pessoa.telefone"]').click().type('6298486-1564')
    cy.get('[data-cy="funcionario.pessoa.email"]').click().type('funcionario@ads.com')
    cy.get('[data-cy="funcionario.pessoa.estadoBrasileiro"]').click()
    cy.get('li[aria-label="GOIAS"]').scrollIntoView().click()
    cy.get('[data-cy="funcionario.login"]').click().type('admin')
    cy.get('[data-cy="funcionario.senha"]').click().type('admin')
    cy.get('[data-cy="funcionario.comissao"]').click().type('5')
    cy.wait(3000)
    cy.contains('button', 'Salvar Funcionário').click()
    cy.contains("div[role='alert']", "Funcionário Inserido!").should("be.visible");
    cy.contains('td', 'Funcionário Teste').should('be.visible')
    cy.wait(2000)

    cy.step('Adicionando Agendamento')
    cy.get('li[routerlink="/agendamentos"]').should('be.visible').click()
    cy.url().should('contain', '/agendamentos')
    cy.wait(2000)
    cy.contains('button', 'Adicionar').should('be.visible').click()
    cy.contains('span', 'Cadastro de Agendamento').should('be.visible')
    cy.get('[data-cy="agendamento.data"]').type('15/06/2023')
    cy.get('[data-cy="agendamento.hora"]').type('10:30')
    cy.get('[data-cy="agendamento.cliente"]').click()
    cy.get('li[aria-label="Cliente Teste"]').click()
    cy.get('[data-cy="agendamento.servico"]').click()
    cy.get('li[aria-label="Corte de Cabelo e Escova"]').click()
    cy.get('[data-cy="agendamento.funcionario"]').click()
    cy.get('li[aria-label="Funcionário Teste"]').click()
    cy.get('[data-cy="agendamento.observacao"]').click().type('Teste Automação')
    cy.contains('button', 'Salvar Agendamento').click()
    cy.contains("div[role='alert']", "Agendamento Inserido!").should("be.visible");
    cy.wait(3000)

    cy.step('Excluindo Agendamento')
    cy.get('[data-cy="agendamento-excluir"]').first().click()
    cy.contains('button', 'Excluir').click()
    cy.contains("div[role='alert']", "Agendamentos excluído!").should("be.visible");
    cy.wait(3000)

    cy.step('Excluindo Funcionário')
    cy.get('li[routerlink="/funcionarios"]').should('be.visible').click()
    cy.url().should('contain', '/funcionarios')
    cy.wait(3000)
    cy.get('[data-cy="funcionario-excluir"]').first().click()
    cy.contains('button', 'Excluir').click()
    cy.contains("div[role='alert']", "Funcionário excluído!").should("be.visible");
    cy.wait(3000)

    cy.step('Excluindo Cliente')
    cy.get('li[routerlink="/clientes"]').should("be.visible").click();
    cy.url().should("contain", "/clientes");
    cy.wait(3000);
    cy.get('[data-cy="cliente-excluir"]').first().click()
    cy.contains('button', 'Excluir').click()
    cy.contains("div[role='alert']", "Clientes excluído!").should("be.visible");
    cy.wait(3000)
    cy.get('li[routerlink="/home"]').click()
    cy.contains("span", "BeautySync,").should("be.visible");
    cy.wait(3000)

  }
}

export default new BeautySync();
