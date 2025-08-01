package web.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().contains("inventory");
    }
}
