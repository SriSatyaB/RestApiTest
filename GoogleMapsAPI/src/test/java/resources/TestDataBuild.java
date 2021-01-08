package resources;

import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad(String name,String language,String address) {
		AddPlace addPlace= new AddPlace();
		addPlace.setName(name);
		addPlace.setLanguage(language);
		addPlace.setAddress(address);
		addPlace.setAccuracy(50);
		
		
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite("http://google.com");
		
		//types
		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		addPlace.setTypes(list);
		
		//location
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		addPlace.setLocation(loc);
		return addPlace;
	}
	public String deletePlacePayLoad(String placeId) {
		return "{\r\n" + 
				"    \"place_id\":\""+placeId+"\"   	 	\r\n" + 
				"}";
	}
	public String putPlacePayLoad(String address,String placeId) {
		return "{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}";
	}

}
