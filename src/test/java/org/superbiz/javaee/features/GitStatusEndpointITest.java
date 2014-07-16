package org.superbiz.javaee.features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"html:target/cucumber-html-report"}, tags = {"@git_status_endpoint"}, glue = {"org.superbiz.javaee"})
public class GitStatusEndpointITest {
}
