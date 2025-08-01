package api.tests;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("Cucumber")
@SelectClasspathResource("features/api")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@api")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "api.stepdefinitions")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/api/cucumber-reports-api-test.html")

public class TestRunner {
}