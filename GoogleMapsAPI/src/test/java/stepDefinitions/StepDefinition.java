package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import static org.junit.Assert.*;

public class StepDefinition extends Utils {

	RequestSpecification request;
	ResponseSpecification res;
	io.restassured.response.Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		request = given().spec(reqSpec()).body(data.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		APIResources resourceApi = APIResources.valueOf(resource);

		res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

		if (httpMethod.equalsIgnoreCase("post"))
			response = request.when().post(resourceApi.getResource());
		else if (httpMethod.equalsIgnoreCase("get"))
			response = request.when().get(resourceApi.getResource());
		else if (httpMethod.equalsIgnoreCase("put"))
			response = request.when().put(resourceApi.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int code) {
		assertEquals(code, response.getStatusCode());

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		System.out.println(getJsonKeyValue(response, key));
		org.junit.Assert.assertEquals(value, getJsonKeyValue(response, key));
	}

	@Then("verify place_Id created is mapped to {string} using {string}")
	public void verify_place_id_created_is_mapped_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonKeyValue(response, "place_id");
		request = given().spec(reqSpec()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "get");

		String actualName = getJsonKeyValue(response, "name");
		org.junit.Assert.assertEquals(actualName, expectedName);

	}

	@Given("DeletePlaceAPI Payload")
	public void delete_place_api_payload() throws IOException {
		request = given().spec(reqSpec()).body(data.deletePlacePayLoad(place_id));

	}

	@Then("verify place is deleted successfully using {string} message displayed in {string}")
	public void verify_place_is_deleted_successfully_using(String expectedMsg, String resource) throws IOException {
		request = given().spec(reqSpec()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "get");
		String actualMsg = getJsonKeyValue(response, "msg");
		org.junit.Assert.assertEquals(actualMsg, expectedMsg);

	}

	@Given("PutPlaceAPI PayLoad with {string}")
	public void put_place_api_pay_load(String address) throws IOException {
		request = given().spec(reqSpec()).queryParam("place_id", place_id)
				.body(data.putPlacePayLoad(address, place_id));
	}

	@Then("verify place is updated successfully with {string} using {string}")
	public void verify_place_is_updated_successfully_with(String expectedAddress, String resource) throws IOException {
		request = given().spec(reqSpec()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "get");
		String actualAddress = getJsonKeyValue(response, "address");
		assertEquals(actualAddress, expectedAddress);
	}

}
