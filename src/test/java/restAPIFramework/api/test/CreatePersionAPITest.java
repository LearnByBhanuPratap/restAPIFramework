package restAPIFramework.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import restAPIFramework.com.rest.responsepojo.CreatePersonResponse;
import restAPIFramework.com.rest.service.Service;

public class CreatePersionAPITest extends TestBase{
	
	String name;
	String surname;
	String city;
	String landmark;
	String state;
	String zipcode;
	
	@BeforeClass
	public void dataSetUp(){
		name = "serviceTest";
		surname = "servicesurname";
		city = "servicecity";
		landmark = "servicelandmark";
		state = "statestate";
		zipcode = "560078";
	}
	
	
	@Test
	public void createPersionAPITest(){
		service = new Service();
		response =  service.createPersionAPI(name, surname, city, landmark, state, zipcode);
		
		if(response.getStatusCode() == 200){

			System.out.println(response.asString());

			Gson gson = new Gson();
			CreatePersonResponse createPersonResponse = gson.fromJson(response.asString(), CreatePersonResponse.class);

			// Just printing to see data from response.
			System.out.println(createPersonResponse.getResponse().get(0).getAddress().getCity());
			System.out.println(createPersonResponse.getResponse().get(0).getName());
			System.out.println(createPersonResponse.getResponse().get(0).getSurname());
			System.out.println(createPersonResponse.getResponse().get(0).getAddress().getLandmark());

			Assert.assertEquals(createPersonResponse.getResponse().get(0).getAddress().getCity(), city);

			Assert.assertEquals(createPersonResponse.getResponse().get(0).getName(), name);
		}
		else{
			Assert.assertTrue(false, response.asString());
		}
	}

}
