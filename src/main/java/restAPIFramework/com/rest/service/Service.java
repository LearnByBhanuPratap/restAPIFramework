package restAPIFramework.com.rest.service;

import org.json.JSONObject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import restAPIFramework.com.rest.requestpojo.Address;
import restAPIFramework.com.rest.requestpojo.CreatePerson;

public class Service {
	
	public Response createPersionAPI(String name,String surname, String city,String landmark, String state, String zipcode){
		CreatePerson createPerson = new CreatePerson();
		createPerson.setName(name);
		createPerson.setSurname(surname);
		
		Address address = new Address();
		createPerson.setAddress(address);
		address.setCity(city);
		address.setLandmark(landmark);
		address.setState(state);
		address.setZipcode(zipcode);
		
		JSONObject jsonObj = new JSONObject(createPerson);
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType("application/json");
		requestSpecification.accept("application/json");
		requestSpecification.body(jsonObj);
		Response response = requestSpecification.post(ServiceURL.createPersonurl);
		return response;
	}

}
