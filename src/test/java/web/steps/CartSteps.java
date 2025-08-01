package web.steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import web.pages.CartPage;
import web.pages.InventoryPage;

import static org.junit.Assert.*;

public class CartSteps {
    WebDriver driver = web.hooks.Hooks.driver;
    InventoryPage inventoryPage = new InventoryPage(driver);
    CartPage cartPage = new CartPage(driver);

    @When("User adds {string} to the cart")
    public void user_adds_item(String productName) {
        inventoryPage.addToCart(productName);
    }

    @When("User removes {string} from the cart")
    public void user_removes_item(String productName) {
        cartPage.removeItem(productName);
    }

    @When("User navigates to the cart page")
    public void user_navigates_to_cart() {
        inventoryPage.goToCart();
    }

    @Then("{string} should be in the cart")
    public void item_should_be_in_cart(String productName) {
        assertTrue(cartPage.isItemInCart(productName));
    }

    @Then("{string} should not be in the cart")
    public void item_should_not_be_in_cart(String productName) {
        assertFalse(cartPage.isItemInCart(productName));
    }
}
