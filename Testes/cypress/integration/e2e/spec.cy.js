import beautsync from "../../support/E2ECommands/beautsync";

describe("Apresentação Sistema", () => {
  Cypress._.times(10, () => {
    it("Fluxo de Apresentação do Sistema", () => {
      beautsync.Cadastrar();
    });
  });
});
