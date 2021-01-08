package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification req;
	ResponseSpecification res;
	
	public RequestSpecification reqSpec() throws IOException {
		
		
		if(req==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		// RestAssured.baseURI="https://reqres.in";
		 req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri")).addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).
				 addFilter(ResponseLoggingFilter.logResponseTo(log)).
				 setContentType(ContentType.JSON).build();
		 return req;
		}
		else
			return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
		
	}
	public String getJsonKeyValue(Response resp,String key) {
		String res_str = resp.asString();
		System.out.println(res_str);
		JsonPath json = new JsonPath(res_str);
		return json.get(key).toString();
	}

}
