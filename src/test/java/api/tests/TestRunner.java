package api.tests;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/api", // sesuaikan path feature kamu
        glue = {"api.steps"},                         // package tempat step definitions
        plugin = {
                "pretty",
                "html:reports/html/api-report.html",
                "json:reports/json/api-report.json"
        },
        monochrome = true,
        tags = "@api" // opsional, bisa dihapus kalau mau jalanin semua
)
public class TestRunner {
}