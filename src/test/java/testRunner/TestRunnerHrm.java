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
		plugin= {"pretty","html:test-output"}
		
		)
public class TestRunnerHrm {

}
