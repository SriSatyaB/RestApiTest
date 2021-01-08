Feature: Validating Place APIs 

@AddPlace 
Scenario Outline: verify if place is addding successfully added using AddPlaceAPI 
	Given Add Place Payload with "<name>" "<language>" "<address>" 
	When user calls "AddPlaceAPI" with "Post" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_Id created is mapped to "<name>" using "GetPlaceAPI" 
	
Examples: 
	|name|language|address|
	|satya|English|Warburton ave|
#   |pradeep|English|Fremont ave|

@PutPlace
Scenario Outline:  verify id place is updating successfully using  PutPlaceAPI
	Given PutPlaceAPI PayLoad with "<address>" 
	When user calls "PutPlaceAPI" with "put" http request
	Then the API call got success with status code 200
	And "msg" in response body is "Address successfully updated"
	And verify place is updated successfully with "<address>" using "GetPlaceAPI"
Examples:
|address|
|3430 warburton ave|	
@DeletePlace 
Scenario: verify if place is deleting successfully using DeletePlaceAPI 
	Given DeletePlaceAPI Payload 
	When user calls "DeletePlaceAPI" with "Post" http request 
	Then the API call got success with status code 200 
	And "status" in response body is "OK"
	And verify place is deleted successfully using "Get operation failed, looks like place_id  doesn't exists" message displayed in "GetPlaceAPI"
			
