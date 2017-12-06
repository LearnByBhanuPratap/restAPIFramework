package restAPIFramework.com.rest.responsepojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetStateDetail {

@SerializedName("name")
@Expose
private String name;
@SerializedName("population")
@Expose
private String population;
@SerializedName("language")
@Expose
private String language;
@SerializedName("region")
@Expose
private String region;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPopulation() {
return population;
}

public void setPopulation(String population) {
this.population = population;
}

public String getLanguage() {
return language;
}

public void setLanguage(String language) {
this.language = language;
}

public String getRegion() {
return region;
}

public void setRegion(String region) {
this.region = region;
}

}