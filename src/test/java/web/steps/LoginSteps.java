package web.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import web.pages.InventoryPage;
import web.pages.LoginPage;

import static org.junit.Assert.*;

public class LoginSteps {
    WebDriver driver = web.hooks.Hooks.driver;
    LoginPage loginPage = new LoginPage(driver);
    InventoryPage inventoryPage = new InventoryPage(driver);

    @Given("User is on the Saucedemo login page")
    public void user_is_on_login_page() {
        loginPage.open();
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("User clicks the login button")
    public void user_clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("User should be redirected to the inventory page")
    public void user_should_see_inventory_page() {
        assertTrue(inventoryPage.isOnPage());
    }

    @Then("An error message should be displayed")
    public void error_message_should_be_displayed() {
        assertTrue(loginPage.getErrorMessage().toLowerCase().contains("epic sadface"));
    }

    @Given("User is logged in with valid credentials")
    public void user_logged_in() {
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        assertTrue(inventoryPage.isOnPage());
    }
}
