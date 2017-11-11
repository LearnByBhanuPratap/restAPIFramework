package restAPIFramework.com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import restAPIFramework.com.rest.requestpojo.Address;
import restAPIFramework.com.rest.requestpojo.CreatePerson;

public class Service {
	List<JSONObject> list;
	
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
		System.out.println("json payload..");
		list = new ArrayList<JSONObject>();
		list.add(jsonObj);
		System.out.println(list);
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType("application/json");
		requestSpecification.accept("application/json");
		requestSpecification.body(list.toString());
		System.out.println("end point url.."+ServiceURL.createPersonurl);
		Response response = requestSpecification.post(ServiceURL.createPersonurl);
		return response;
	}
	
	public static void main(String[] args) {
		Service service = new Service();
		Response data = service.createPersionAPI("name", "surname", "city", "landmark", "state", "560072");
		System.out.println(data.asString());
	}

}
