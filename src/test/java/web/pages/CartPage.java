package web.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItemInCart(String productName) {
        return driver.getPageSource().contains(productName);
    }

    public void removeItem(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    public void clickCheckout() {
        driver.findElement(By.id("checkout")).click();
    }
}
