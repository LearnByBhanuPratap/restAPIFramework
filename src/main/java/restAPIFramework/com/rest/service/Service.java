package restAPIFramework.com.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import restAPIFramework.com.rest.requestpojo.Address;
import restAPIFramework.com.rest.requestpojo.CreatePerson;
import restAPIFramework.com.rest.responsepojo.CreatePersonResponse;

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
		
		if(data.getStatusCode() == 200){

			System.out.println(data.asString());

			Gson gson = new Gson();
			CreatePersonResponse createPersonResponse = gson.fromJson(data.asString(), CreatePersonResponse.class);

			System.out.println(createPersonResponse.getResponse().get(0).getAddress().getCity());
			System.out.println(createPersonResponse.getResponse().get(0).getName());
			System.out.println(createPersonResponse.getResponse().get(0).getSurname());
			System.out.println(createPersonResponse.getResponse().get(0).getAddress().getLandmark());

			Assert.assertEquals(createPersonResponse.getResponse().get(0).getAddress().getCity(), "city");

			Assert.assertEquals(createPersonResponse.getResponse().get(0).getName(), "name");
		}
	}

}
