package com.example.javaee.features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"html:target/cucumber-html-report"}, tags = {"@users_endpoint"}, glue = {"com.example.javaee"})
public class UsersEndpointITest {
}
