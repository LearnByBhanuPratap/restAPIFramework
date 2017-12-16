package restAPIFramework.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import restAPIFramework.com.rest.responsepojo.UpdatePersonResponsePojo;
import restAPIFramework.com.rest.service.Service;

public class UpdatePersonDeatils extends TestBase{

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
	public void UpdatePersonDeatilsAPITest(){
		service = new Service();
		response =  service.updatePersonDetail(name, surname, city, landmark, state, zipcode);
		
		if(response.getStatusCode() == 200){

			System.out.println(response.asString());

			Gson gson = new Gson();
			UpdatePersonResponsePojo updatePersonResponsePojo = gson.fromJson(response.asString(), UpdatePersonResponsePojo.class);

			// Just printing to see data from response.
			System.out.println(updatePersonResponsePojo.getResponse().get(0).getAddress().getCity());
			System.out.println(updatePersonResponsePojo.getResponse().get(0).getName());
			System.out.println(updatePersonResponsePojo.getResponse().get(0).getSurname());
			System.out.println(updatePersonResponsePojo.getResponse().get(0).getAddress().getLandmark());

			Assert.assertEquals(updatePersonResponsePojo.getResponse().get(0).getAddress().getCity(), city);

			Assert.assertEquals(updatePersonResponsePojo.getResponse().get(0).getName(), name);
		}
		else{
			Assert.assertTrue(false, response.asString());
		}
	}

}
