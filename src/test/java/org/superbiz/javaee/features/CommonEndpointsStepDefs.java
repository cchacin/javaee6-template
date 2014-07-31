package org.superbiz.javaee.features;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

public class CommonEndpointsStepDefs {

	private Response response;
	public static final String BASE_URL = "http://localhost:8080/";
	final WebClient webClient = WebClient.create(BASE_URL).accept(
			APPLICATION_JSON);

	private String authorizationHeader;

	private static String appName = "functional_tests";

	private String getContentFromResourceFilePath(String postBodyFilePath) {
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(getClass().getResource(
					postBodyFilePath).toURI()));
		} catch (IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}

		return StandardCharsets.UTF_8.decode(ByteBuffer.wrap(encoded))
				.toString();
	}

	@Given("^header \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void header_with_value(String headerName, String headerValue)
			throws Throwable {
		this.webClient.header(headerName, headerValue);
	}

	@Given("^header \"([^\"]*)\" has not been set$")
	public void header_has_not_been_set(String headerName) throws Throwable {
	}

	@Given("^I made a (GET|HEAD) call to \"([^\"]*)\" endpoint$")
	public void I_made_a_GET_HEAD_call_to_endpoint(final String method,
			final String endpointUrl) throws Throwable {
		WebClient client = WebClient.create(BASE_URL);
		if (this.authorizationHeader != null) {
			this.webClient.header("Authorization", this.authorizationHeader);
		}
		Response getResponse = (method.equals("GET")) ? client
				.path(endpointUrl).get() : client.path(endpointUrl).head();
		assertThat(getResponse.getStatus()).isBetween(200, 299);
	}

	@Given("^I made a (POST|PUT) call to \"([^\"]*)\" endpoint with post body:$")
	public void I_made_the_POST_PUT_call_to_endpoint_with_post_body(
			String method, String endpointUrl, final String postBody)
			throws Throwable {
		WebClient client = WebClient.create(BASE_URL);
		if (this.authorizationHeader != null) {
			this.webClient.header("Authorization", this.authorizationHeader);
		}
		Response getResponse = (method.equals("POST")) ? client.path(
				endpointUrl).post(postBody) : client.path(endpointUrl).put(
				postBody);
		assertThat(getResponse.getStatus()).isBetween(200, 299);
	}

	@Given("^I made a (POST|PUT) call to \"([^\"]*)\" endpoint with post body in file \"([^\"]*)\"$")
	public void I_made_a_POST_call_to_endpoint_with_post_body_in_file(
			String method, String endpointUrl, final String postBodyFilePath)
			throws Throwable {
		String postBody = this.getContentFromResourceFilePath(postBodyFilePath);
		I_made_the_POST_PUT_call_to_endpoint_with_post_body(method,
				endpointUrl, postBody);
	}

	@Given("^wait for \"([^\"]*)\" seconds for the background processes to finish$")
	public void wait_for_seconds_for_the_background_processes_to_finish(
			Integer timeToWaitInSeconds) throws Throwable {
		Thread.sleep(timeToWaitInSeconds * 1_000);
	}

	@Given("^I made a DELETE call to \"([^\"]*)\" endpoint$")
	public void I_made_a_DELETE_call_to_endpoint(String endpointUrl)
			throws Throwable {
		WebClient client = WebClient.create(BASE_URL);
		if (this.authorizationHeader != null) {
			this.webClient.header("Authorization", this.authorizationHeader);
		}
		Response getResponse = client.path(endpointUrl).delete();
		assertThat(getResponse.getStatus()).isBetween(200, 299);
	}

	@Given("^I made a (GET|HEAD) call to \"([^\"]*)\" endpoint with header \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void I_made_a_GET_HEAD_call_to_endpoint_with_header_with_value(
			final String method, final String endpointUrl, String headerName,
			String headerValue) throws Throwable {
		WebClient client = WebClient.create(BASE_URL);
		client.header(headerName, headerValue);
		if (this.authorizationHeader != null) {
			this.webClient.header("Authorization", this.authorizationHeader);
		}
		Response getResponse = (method.equals("GET")) ? client
				.path(endpointUrl).get() : client.path(endpointUrl).head();
		assertThat(getResponse.getStatus()).isBetween(200, 299);
	}

	@When("^I make a (GET|HEAD) call to \"([^\"]*)\" endpoint$")
	public final void I_make_a_GET_HEAD_call_to_endpoint(final String method,
			final String endpointUrl) throws Throwable {
		if (this.authorizationHeader != null) {
			this.webClient.header("Authorization", this.authorizationHeader);
		}
		this.response = (method.equals("GET")) ? this.webClient.path(
				endpointUrl).get() : this.webClient.path(endpointUrl).head();
	}

	@When("^I make a (POST|PUT) call to \"([^\"]*)\" endpoint with post body:$")
	public void I_make_a_POST_PUT_call_to_endpoint_with_post_body(
			String method, String endpointUrl, final String postBody)
			throws Throwable {
		if (this.authorizationHeader != null) {
			this.webClient.header("Authorization", this.authorizationHeader);
		}
		this.response = (method.equals("POST")) ? this.webClient.path(
				endpointUrl).post(postBody) : this.webClient.path(endpointUrl)
				.put(postBody);
	}

	@When("^I make a (POST|PUT) call to \"([^\"]*)\" endpoint with post body in file \"([^\"]*)\"$")
	public void I_make_a_POST_PUT_call_to_endpoint_with_post_body_in_file(
			String method, String endpointUrl, final String postBodyFilePath)
			throws Throwable {
		String content = getContentFromResourceFilePath(postBodyFilePath);

		I_make_a_POST_PUT_call_to_endpoint_with_post_body(method, endpointUrl,
				content);
	}

	@When("^I make a DELETE call to \"([^\"]*)\" endpoint$")
	public final void I_make_a_DELETE_call_to_endpoint(final String endpointUrl)
			throws Throwable {
		if (this.authorizationHeader != null) {
			this.webClient.header("Authorization", this.authorizationHeader);
		}
		this.response = this.webClient.path(endpointUrl).delete();
	}

	@Then("^the response status code should be \"([^\"]*)\"$")
	public final void the_response_status_code_should_be(final String statusCode)
			throws Throwable {
		assertThat(this.response.getStatus()).isEqualTo(
				Integer.valueOf(statusCode));
	}

	@Then("^response content type should be \"([^\"]*)\"$")
	public final void response_content_type_should_be(final String contentType)
			throws Throwable {
		assertThat(contentType).isEqualTo(
				this.response.getMetadata().getFirst("content-type"));
	}

	@Then("^response should be json in file \"([^\"]*)\"$")
	public void response_should_be_json_responseBody(
			final String contentFilePath) throws Throwable {

		String content = getContentFromResourceFilePath(contentFilePath);
		this.response_should_be_json(content);
	}

	@Then("^response should be json:$")
	public void response_should_be_json(final String jsonResponseString)
			throws Throwable {

		String value = IOUtils
				.toString((InputStream) this.response.getEntity());

		assertThatJson(value).ignoring("${json-unit.ignore}").isEqualTo(
				jsonResponseString);
	}

	@Then("^response should be empty$")
	public void response_should_be_empty() throws Throwable {
		String value = IOUtils
				.toString((InputStream) this.response.getEntity());
		assertThat(value).isEmpty();
	}

	@Then("^response should be file \"([^\"]*)\"$")
	public void response_should_be_file(final String contentFilePath)
			throws Throwable {
		String value = IOUtils
				.toString((InputStream) this.response.getEntity());

		String content = getContentFromResourceFilePath(contentFilePath);

		assertThat(value).isEqualTo(content);
	}

	@Then("^response header \"([^\"]*)\" should be \"([^\"]*)\";$")
	public void response_header_should_be_(String responseHeaderName,
			String headerValue) throws Throwable {
		System.out.println(this.response.getMetadata());
	}
}
