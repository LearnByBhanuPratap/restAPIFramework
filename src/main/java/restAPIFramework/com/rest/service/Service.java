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
import restAPIFramework.com.rest.requestpojo.UpdatePersonPojo;
import restAPIFramework.com.rest.responsepojo.CreatePersonResponse;

public class Service {
	List<JSONObject> list;
	
	/**
	 * This API will create Person
	 * @param name
	 * @param surname
	 * @param city
	 * @param landmark
	 * @param state
	 * @param zipcode
	 * @return
	 */
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
	
	/**
	 * This API will return state details
	 * @return
	 */
	public Response getStateDetails(){
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType("application/json");
		requestSpecification.accept("application/json");
		System.out.println("end point url.."+ServiceURL.createPersonurl);
		Response response = requestSpecification.get(ServiceURL.getStateDetails);
		return response;
	}
	
	/**
	 * This API will update person details
	 * @param name
	 * @param surname
	 * @param city
	 * @param landmark
	 * @param state
	 * @param zipcode
	 * @return
	 */
	public Response updatePersonDetail(String name,String surname, String city,String landmark, String state, String zipcode){
		UpdatePersonPojo updatePersonPojo = new UpdatePersonPojo();
		updatePersonPojo.setName(name);
		updatePersonPojo.setSurname(surname);
		
		Address address = new Address();
		updatePersonPojo.setAddress(address);
		address.setCity(city);
		address.setLandmark(landmark);
		address.setState(state);
		address.setZipcode(zipcode);
		updatePersonPojo.setAddress(address);
		
		JSONObject jsonObj = new JSONObject(updatePersonPojo);
		System.out.println("json payload..");
		list = new ArrayList<JSONObject>();
		list.add(jsonObj);
		System.out.println(list);
		
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType("application/json");
		requestSpecification.accept("application/json");
		requestSpecification.body(list.toString());
		System.out.println("end point url.."+ServiceURL.updatePersonDetails);
		Response response = requestSpecification.put(ServiceURL.updatePersonDetails);
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
