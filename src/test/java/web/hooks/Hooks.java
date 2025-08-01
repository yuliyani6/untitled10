package web.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Deteksi OS Windows, hanya gunakan user-data-dir di laptop pribadi
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            options.addArguments("--user-data-dir=C:/Users/YOUR_USERNAME/AppData/Local/Google/Chrome/User Data");
        }

        // Supaya bisa jalan di GitHub Actions
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
