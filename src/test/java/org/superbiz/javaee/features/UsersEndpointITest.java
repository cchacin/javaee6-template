package org.superbiz.javaee.features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber",
		"json:target/cucumber.json"}, tags = {"@users_endpoint"}, glue = {"org.superbiz.javaee"})
public class UsersEndpointITest {
}
