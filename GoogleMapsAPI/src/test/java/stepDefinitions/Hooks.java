package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	StepDefinition def;

	@Before("@DeletePlace")
	public void deletePayLoad() throws IOException {
		def = new StepDefinition();
		if (StepDefinition.place_id == null) {
			def.add_place_payload_with("pradeep", "French", "Fremont street");
			def.user_calls_with_http_request("AddPlaceAPI", "Post");
			def.verify_place_id_created_is_mapped_to_using("pradeep", "GetPlaceAPI");
		}

	}

	@Before("@PutPlaceAPI")
	public void putPayLoad() throws IOException {
		def = new StepDefinition();
		if (StepDefinition.place_id == null) {
			def.add_place_payload_with("pradeep", "French", "Fremont street");
			def.user_calls_with_http_request("AddPlaceAPI", "Post");
			def.verify_place_id_created_is_mapped_to_using("pradeep", "GetPlaceAPI");
		}
	}

}
