const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
    baseUrl: "http://localhost:4200/",
    env: {
      apiUrl: "http://localhost:8080/api/v1",
    },
    experimentalRunAllSpecs: true,
    watchForFileChanges: false,
    specPattern: "cypress/integration/**/*.cy.{js,jsx,ts,tsx}",
    viewportWidth: 1920,
    viewportHeight: 1080,
    chromeWebSecurity: false,
    scrollBehavior: false,
    defaultCommandTimeout: 60000,
    requestTimeout: 1600000,
    responseTimeout: 1600000,
    pageLoadTimeout: 60000,
    videoUploadOnPasses: false,
    video: false,
    screenshotOnRunFailure: true,
    screenshotsFolder: "cypress/results/screenshots",
    videosFolder: "cypress/results/videos",
  },
});
