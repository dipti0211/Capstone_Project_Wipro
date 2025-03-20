package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"stepdefinitions"},
		plugin = {
				"pretty",
				"json:target/cucumber-reports-json/Cucumber.json", 
				"html:target/cucumber-reports-html/index.html"
		}
		)
public class TestRunner extends AbstractTestNGCucumberTests {
}
