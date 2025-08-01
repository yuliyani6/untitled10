package web.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/web",
        glue = {"web.steps", "web.hooks"},
        plugin = {
                "pretty",
                "html:target/web/cucumber-html-report.html",
                "json:target/web/cucumber-report.json"
        },
        monochrome = true,
        tags = "@web"
)
public class TestRunner {
}
