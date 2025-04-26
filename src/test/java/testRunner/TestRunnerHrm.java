package testRunner;


import org.junit.runner.RunWith;
//import cucumber.api.*;
//import cucumber.api.junit.Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features/applyleave.feature",
		glue="stepDefinitions",
		dryRun=false,
		plugin= {"pretty",
				"html:target/cucumber-html-report",
		        "json:target/cucumber-report.json"}
		
		)
public class TestRunnerHrm {

}
