package restAPIFramework.com.rest.requestpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

	@SerializedName("city")
	@Expose
	private String city;
	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("zipcode")
	@Expose
	private String zipcode;
	@SerializedName("landmark")
	@Expose
	private String landmark;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

}
