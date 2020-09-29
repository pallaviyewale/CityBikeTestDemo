package Tests;

import static io.restassured.RestAssured.given;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import config.ConfigHelper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.HelperMethods;
import static org.hamcrest.Matchers.*;


public class TestCityBikeApp 
{
	@Test
	  public void verifyCountforAllCitiesForCityBike()
	  {
		  String url = ConfigHelper.getHelper().getUrl();	
		  
		  Response respon = 
		    given().
	  		    header("Content-Type","application/json").
		  	when().get(url).
		  	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		  	      .extract().response();
		  
		  JsonPath jp = HelperMethods.rawToJson(respon);
		  		  
		  List<String> cities = jp.get("networks.location.city");
		  int cityCount = cities.size();
		  Assert.assertEquals(cityCount, 645);
	  }
	
	@Test
	  public void verifyCountforAllComaniesForCityBike()
	  {
		  String url = ConfigHelper.getHelper().getUrl();	
		  
		  Response respon = 
		    given().
	  		    header("Content-Type","application/json").
		  	when().get(url).
		  	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		  	      .extract().response();
		  
		  JsonPath jp = HelperMethods.rawToJson(respon);
		  		  
		  List<String> companies = jp.get("networks.company");
		  int CompanyCount = companies.size();
		  Assert.assertEquals(CompanyCount, 645);
	  }
	
	@Test
	  public void verifyAllCityLocationsHaveAllFieldsForCityBike()
	  {
		  String url = ConfigHelper.getHelper().getUrl();	
		  
		  Response respon = 
		    given().
	  		    header("Content-Type","application/json").
		  	when().get(url).
		  	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		  	      .extract().response();
		  
		  JsonPath jp = HelperMethods.rawToJson(respon);
		  
		  List<Object> cityDetails  = jp.getList("networks.location");
		  
		  boolean status = false;
		  for(Object cityInfo: cityDetails){
			  String cityInfoDetail = cityInfo.toString();
			  if(cityInfoDetail.contains("country") && cityInfoDetail.contains("city") && cityInfoDetail.contains("latitude") && cityInfoDetail.contains("longitude") )
			  {
				  status = true;
			  }
		  }
		  Assert.assertTrue(status);
	  }
	
	
	@Test
	  public void getTheListOfCitiesForCountryForCityBike()
	  {
		  int CountyCount = 0;
		  String url = ConfigHelper.getHelper().getUrl();	
		  
		  Response respon = 
		    given().
	  		    header("Content-Type","application/json").
		  	when().get(url).
		  	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		  	      .extract().response();
		  
		  JsonPath jp = HelperMethods.rawToJson(respon);
		  
		  List<Object> cityDetails  = jp.getList("networks.location");
		  
		  for(Object cityInfo: cityDetails){
			  String cityInfoDetail = cityInfo.toString();
			  if(cityInfoDetail.contains("DE") )
			  {
				  CountyCount++;
			  }
		  }
		  Assert.assertEquals(CountyCount, 76);
	  }

	@Test
	  public void verifyCityFrankfurtWithAllDetailsForCityBike()
	  {
		  String url = ConfigHelper.getHelper().getUrl();	
		  
		  Response respon = 
		    given().
	  		    header("Content-Type","application/json").
		  	when().get(url).
		  	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		  	      .extract().response();
		  
		  JsonPath jp = HelperMethods.rawToJson(respon);
		  
		  List<Object> cityDetails  = jp.getList("networks.location");
		  
		  for(Object cityInfo: cityDetails){
			  String cityInfoDetail = cityInfo.toString();
			  if(cityInfoDetail.contains("Frankfurt") )
			  {
				 Assert.assertEquals(cityInfoDetail, "{country=DE, city=Frankfurt, latitude=50.1072, longitude=8.66375}");
			  }
		  }
      }	
	
	@Test
	  public void verifyCityPresentsInWorldLocationsForCityBike()
	  {
		  String url = ConfigHelper.getHelper().getUrl();	
		  
		    given().
	  		    header("Content-Type","application/json").
		  	when().get(url).
		  	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		  	      .body("networks.location.city",hasItems("Bærum","Stavanger","Arezzo","Frosinone"));
		 
	  }
	
	@Test
	  public void verifyErrorAfterSendingWrongURLToCityBike()
	  {
		  String url = "http://api.citybik.es/v2/networks/location";
		  
		    given().
	  		    header("Content-Type","application/json").
		  	when().get(url).
		  	then().assertThat().statusCode(404);
	  }
}
