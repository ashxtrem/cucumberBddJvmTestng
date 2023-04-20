package runners;

import base.BaseTest;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"classpath:featureFiles"},
        glue = "classpath:stepDefinitions",
        plugin = { "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber.html"})
public class TestRunner extends BaseTest {
}
