package web.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import web.pages.CartPage;
import web.pages.CheckoutPage;
import web.pages.OverviewPage;

import static org.junit.Assert.*;

public class CheckoutSteps {
    WebDriver driver = web.hooks.Hooks.driver;
    CartPage cartPage = new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);
    OverviewPage overviewPage = new OverviewPage(driver);

    @When("User clicks the checkout button")
    public void user_clicks_checkout() {
        cartPage.clickCheckout();
    }

    @When("User enters first name {string}, last name {string}, and postal code {string}")
    public void user_enters_checkout_info(String first, String last, String zip) {
        checkoutPage.enterInformation(first, last, zip);
    }

    @When("User clicks the continue button")
    public void user_clicks_continue() {
        checkoutPage.clickContinue();
    }

    @When("User clicks the finish button")
    public void user_clicks_finish() {
        checkoutPage.clickFinish();
    }

    @Then("User should see the confirmation message {string}")
    public void user_sees_confirmation(String expected) {
        assertEquals(expected, overviewPage.getConfirmationMessage());
    }
}